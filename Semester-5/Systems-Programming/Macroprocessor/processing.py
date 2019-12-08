from collections import OrderedDict

macro_name_table = OrderedDict()
macro_definition_table = list()
keyword_parameter_default_table = list()

parameter_name_tables = list()

mdt_pointer = 0
kpd_pointer = 0

pn_pointer = 0

source_code = None

with open("macro-example.txt", "r") as inp:
    source_code = inp.readlines()

macro_encountered = False
macro_name_added = False
macro_ended = False

for line_number, line in enumerate(source_code, 1):
    line = line.strip('\n').strip()

    if 'START' in line:
        break

    if not macro_encountered:
        if 'MACRO' in line.upper():
            macro_encountered = True
            continue

        if macro_name_added:

            if 'MEND' in line:
                pn_pointer = pn_pointer + 1
                macro_definition_table.append(line.strip())
                mdt_pointer += 1
                macro_ended = True
                continue

            instruction, args = [l.strip() for l in line.split(' ', 1)]
            current_definition = f"{instruction} "

            if ',' in args:
                args = args.split(",")
                for arg in args:
                    arg = arg.strip()
                    try:
                        index = parameter_name_tables[pn_pointer].index(arg)
                        current_definition += f"(P,{index}),"
                    except ValueError:
                        current_definition += f"{arg}, "
                current_definition = current_definition[0: len(current_definition) - 1]
            else:
                args = args.strip()
                try:
                    index = parameter_name_tables[pn_pointer].index(args)
                    current_definition += f"(P,{index})"
                except ValueError:
                    current_definition += f"{args}"
            macro_definition_table.append(current_definition)
            mdt_pointer += 1

    else:
        name, parameters = [i.strip() for i in line.split(' ', 1)]
        macro_name_table[name] = {
            'mdtp': mdt_pointer,
            'kpdtp': kpd_pointer,
            '#pp': 0,
            '#kp': 0
        }
        parameter_name_tables.append([])
        parameters = parameters.split(',')
        for param in parameters:
            param = param.strip()
            if '=' in param:
                p, default = param.split('=')
                if default == '':
                    default = None

                parameter_name_tables[pn_pointer].append(p)

                keyword_parameter_default_table.append((p, default))
                macro_name_table[name]['#kp'] += 1
                kpd_pointer = kpd_pointer + 1
            else:
                parameter_name_tables[pn_pointer].append(param)
                macro_name_table[name]['#pp'] += 1
        macro_name_added = True
        macro_encountered = False

from pprint import pprint

pprint(macro_definition_table)
pprint(keyword_parameter_default_table)
pprint(macro_name_table)

from pickle import dump

with open("mdt.pkl", "wb+") as mdt:
    dump(macro_definition_table, mdt)

with open("mnt.pkl", "wb+") as mnt:
    dump(macro_name_table, mnt)

with open("kpdt.pkl", "wb+") as kpdt:
    dump(keyword_parameter_default_table, kpdt)

with open("pn.pkl", "wb+") as pn:
    dump(parameter_name_tables, pn)