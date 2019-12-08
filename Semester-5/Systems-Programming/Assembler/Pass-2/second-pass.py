from sys import exit
from pickle import load

intermediate_code = None
symbol_table = None
literal_table = None
pool_table = None

with open("../intermediate-code.txt", "r") as ic:
    intermediate_code = ic.readlines()

with open("../symtab.pkl", "rb+") as symtab:
    symbol_table = load(symtab)

with open("../littab.pkl", "rb+") as littab:
    literal_table = load(littab)

with open("../pooltab.pkl", "rb+") as pooltab:
    pool_table = load(pooltab)

machine_code_buffer = ""
machine_code_area = []

pooltab_ptr = 1
littab_length = len(literal_table)

for intermediate_line in intermediate_code:
    location_counter, instructions = [l.strip() for l in intermediate_line.strip().split(":")]
    klass_of_instruction = None
    opcode = None
    operands = None
    
    flag = 0

    location_counter = int(location_counter)

    try:
        temp, operands = instructions.split(' ', 1)
    except ValueError:
        temp = instructions

    temp = temp.replace("(", "").replace(")", "")
    klass_of_instruction, opcode = temp.split(",")

    if klass_of_instruction == "AD":
        machine_code_buffer = f"{location_counter} : "
        if opcode == "01":
            continue
        
        elif opcode in ("02, 03"):
            
            start = pool_table[pooltab_ptr - 1]
            end = littab_length
            if pool_table[pooltab_ptr] < littab_length:
                end = pool_table[pooltab_ptr]
                pooltab_ptr += 1

            for i in range(start, end):
                machine_code_buffer += f"{literal_table[i][0]}"
                location_counter += 2
                if i != end - 1:
                    machine_code_buffer += f"\n{location_counter} : "
    
    elif klass_of_instruction == "DL":
        machine_code_buffer = f"{location_counter} : "
        if opcode == "01":
            machine_code_buffer += operands.replace("(", "").replace(")", "").split(",")[1]
        else:
            machine_code_buffer += '$' * int(operands.replace("(", "").replace(")","").split(",")[1])
    
    else:
        machine_code_buffer = f"{location_counter} : {opcode} "
        
        op1 = None
        op2 = None

        try:
            op1, op2 = operands.split(' ')
        except:
            op1 = operands.strip()

        if op1 in ("1", "2", "3", "4"):
            machine_code_buffer += f"{op1} "
        else:
            if 'S' in op1:
                op1 = op1.replace("(", "").replace(")", "").split(",")[1]
                for index, key in enumerate(symbol_table, 1):
                    if index == int(op1):
                        machine_code_buffer += f"{symbol_table[key]['ADDRESS']}"
                        break
            else:
                op1 = op1.replace("(", "").replace(")", "").split(",")[1]
                for index, l in enumerate(literal_table, 1):
                    if index == int(op1):
                        machine_code_buffer += f"{l[1]}"
                        break

        if op2 is not None:
            if op2 in ("1", "2", "3", "4"):
                machine_code_buffer += f"{op2}"
            else:
                if 'S' in op2:
                    op2 = op2.replace("(", "").replace(")", "").split(",")[1]
                    for index, key in enumerate(symbol_table, 1):
                        if index == int(op2):
                            machine_code_buffer += f"{symbol_table[key]['ADDRESS']}"
                            break
                else:
                    op2 = op2.replace("(", "").replace(")", "").split(",")[1]
                    for index, l in enumerate(literal_table, 1):
                        if index == int(op2):
                            machine_code_buffer += f"{l[1]}"
                            break

    print(machine_code_buffer)