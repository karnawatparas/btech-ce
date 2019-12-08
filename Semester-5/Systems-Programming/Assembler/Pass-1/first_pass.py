from tables import MACHINE_OPCODE_TABLE as mot, REGISTER_TABLE as rt
from sys import exit
from pickle import dump

SYMBOL_TABLE = {}
LITERAL_TABLE = []
POOL_TABLE = [0, ]

littab_ptr = 0
pooltab_ptr = 0

buffer = ''
INTERMEDIATE_CODE = []

SOURCE_CODE = None

LABEL_INDICATOR = ':'

LOCATION_COUNTER = 0

with open('../source.txt', 'r') as source:
    SOURCE_CODE = source.readlines()

for line_number, line in enumerate(SOURCE_CODE, 1):
    line = line.strip('\n')
    label = None
    mnemonic = None
    operands = None
    if LABEL_INDICATOR in line:
        label, line = line.split(LABEL_INDICATOR, 1)
    line = line.strip()

    if 'DC' in line or 'DS' in line:
        label, mnemonic, operands = line.split(' ')
    else:
        try:
            mnemonic, operands = line.split(' ', 1)
        except ValueError:
            mnemonic = line

    mnemonic = mnemonic.strip()

    buffer = f"{LOCATION_COUNTER} : "

    if label is not None and label not in SYMBOL_TABLE:
        label = label.strip()
        SYMBOL_TABLE[label] = {
            'ADDRESS': LOCATION_COUNTER,
            'SIZE': 0,
            'VALUE': 0
        }

    if mnemonic == 'START':
        LOCATION_COUNTER = int(operands.strip())
        buffer = buffer + f"({mot[mnemonic]['CLASS']},{mot[mnemonic]['OPCODE']})"
        buffer = buffer + " "
        buffer = buffer + f"(C,{operands.strip()})"

    elif mnemonic in ('DC', 'DS'):
        size = 0
        value = 0
        if mnemonic == 'DC':
            size = 2
            value = operands.strip()
        else:
            size = 2 * (int(operands.strip()))
            value = '$'*(size//2)
        buffer = buffer + f"({mot[mnemonic]['CLASS']},{mot[mnemonic]['OPCODE']})"
        buffer = buffer + " "
        buffer = buffer + f"(C,{operands.strip()})"

        if label in SYMBOL_TABLE:
            SYMBOL_TABLE[label]['ADDRESS'] = LOCATION_COUNTER
            SYMBOL_TABLE[label]['SIZE'] = size
            SYMBOL_TABLE[label]['VALUE'] = value
        else:
            SYMBOL_TABLE[label] = {
                'ADDRESS': LOCATION_COUNTER,
                'SIZE': size,
                'VALUE': value
            }
        LOCATION_COUNTER = LOCATION_COUNTER + size

    elif mnemonic in ('LTORG', 'END'):
        for i in range(POOL_TABLE[pooltab_ptr], littab_ptr):
            LITERAL_TABLE[i][1] = LOCATION_COUNTER
            LOCATION_COUNTER += 2
        POOL_TABLE.append(littab_ptr)
        pooltab_ptr += 1
        buffer = buffer + f"({mot[mnemonic]['CLASS']},{mot[mnemonic]['OPCODE']})"

    else:

        if mnemonic not in mot:
            print(
                f'{line_number}: No such mnemonic found, please specify a valid mnemonic')
            exit(1)

        klass = mot[mnemonic]['CLASS']
        opcode = mot[mnemonic]['OPCODE']
        size = mot[mnemonic]['SIZE']

        buffer = buffer + f"({klass},{opcode})"
        buffer = buffer + " "

        operands = operands.strip()

        def check_operands(_operand):
            global SYMBOL_TABLE, LITERAL_TABLE, littab_ptr
            _buffer = ""
            if _operand in rt:
                _buffer = _buffer + f"{rt[_operand]}"
            else:
                if '=' in _operand:
                    literal = _operand.replace('=', '')
                    LITERAL_TABLE.append([literal, None])
                    littab_ptr += 1
                    _buffer += " "
                    _buffer += f"(L,{littab_ptr})"
                else:
                    if _operand not in SYMBOL_TABLE:
                        SYMBOL_TABLE[_operand] = {
                            'ADDRESS': None,
                            'SIZE': None,
                            'VALUE': None
                        }
                    _buffer += " "
                    index = 0
                    for ind, key in enumerate(SYMBOL_TABLE.keys(), 1):
                        if key == _operand:
                            index = ind
                            break
                    _buffer += f"(S,{index})"
            
            return _buffer.strip()

        if ',' in operands:
            for operand in operands.split(','):
                operand = operand.strip()
                buffer += check_operands(operand)
                buffer += " "
        else:
            buffer += check_operands(operands)
        LOCATION_COUNTER += size

    INTERMEDIATE_CODE.append(buffer)
    buffer = ""

print()
with open("../intermediate-code.txt", "w+") as output:
    for ic in INTERMEDIATE_CODE:
        print(ic)
        output.write(ic + "\n")

with open("../symtab.pkl", "wb+") as symtab:
    dump(SYMBOL_TABLE, symtab)

with open("../littab.pkl", "wb+") as littab:
    dump(LITERAL_TABLE, littab)

with open("../pooltab.pkl", "wb+") as pooltab:
    dump(POOL_TABLE, pooltab)