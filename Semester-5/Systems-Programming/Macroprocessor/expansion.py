from collections import OrderedDict
from pickle import load
from sys import exit

with open("mdt.pkl", "rb+") as mdt:
    macro_definition_table = load(mdt)

with open("mnt.pkl", "rb+") as mnt:
    macro_name_table = load(mnt)

with open("kpdt.pkl", "rb+") as kpd:
    keyword_parameter_default_table = load(kpd)

with open("pn.pkl", "rb+") as pn:
    actual_parameter_tables = load(pn)

with open("macro-example.txt", "r") as inp:
    source_code = inp.readlines()

line_number = 0

while 'START' not in source_code[line_number]:
    line_number += 1

line_number += 1

source_code = source_code[line_number:]

for line in source_code:

    if 'END' in line:
        break

    mnemonic, operands = [l.strip() for l in line.strip().split(' ', 1)]

    if mnemonic not in ("ADD", "MOVER", "MOVEM", "SUB"):

        if mnemonic in macro_name_table:

            index = -1
            for ind, key in enumerate(macro_name_table.keys()):
                if mnemonic == key:
                    index = ind
                    break

            parameters = [o.strip() for o in operands.split(",")]

            for i in range(macro_name_table[mnemonic]["#pp"]):
                if '=' in parameters[i]:
                    print(f'Error: Invalid position of parameters')
                    exit(1)
                actual_parameter_tables[index][i] = parameters[i].strip()

            kpdt = macro_name_table[mnemonic]['kpdtp']
            j = 0

            start = macro_name_table[mnemonic]["#pp"]
            end = macro_name_table[mnemonic]["#pp"] + macro_name_table[mnemonic]['#kp']

            for i in range(start, end):
                actual_parameter_tables[index][i] = keyword_parameter_default_table[kpdt + j][1]
                j = j + 1

            for i in range(macro_name_table[mnemonic]["#pp"], macro_name_table[mnemonic]['#kp'] + 1):
                if '=' in parameters[i]:
                    parameter, value = parameters[i].split('=')
                    parameter, value = parameter.strip(), value.strip()

                    for key in keyword_parameter_default_table:
                        if key[0] == parameter:
                            if value == '':
                                value = key[1]
                            if value is None:
                                print('Provide a value for keyword parameter')
                                exit(1)
                            break
                    actual_parameter_tables[index][i] = value

            
            mdt = macro_name_table[mnemonic]["mdtp"]

            while 'MEND' not in macro_definition_table[mdt]:
                for i in range(len(actual_parameter_tables[index])):
                    if f'(P,{i})' in macro_definition_table[mdt]:
                        macro_definition_table[mdt] = macro_definition_table[mdt].replace(f"(P,{i})", actual_parameter_tables[index][i])

                mdt += 1

        else:
            print('No such macro defined!')
            exit(1)

    else:
        continue

print(macro_definition_table)