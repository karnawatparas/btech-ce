MACHINE_OPCODE_TABLE = {
    'MOVER': {
        'CLASS': 'IS',
        'SIZE': 2,
        'OPCODE': '0100'
    },
    'MOVEM': {
        'CLASS': 'IS',
        'SIZE': 3,
        'OPCODE': '0110'
    },
    'ADD': {
        'CLASS': 'IS',
        'SIZE': 2,
        'OPCODE': '0120'
    },
    'SUB': {
        'CLASS': 'IS',
        'SIZE': 2,
        'OPCODE': '0130'
    },
    'CMP': {
        'CLASS': 'IS',
        'SIZE': 3,
        'OPCODE': '0140'
    },
    'JNZ': {
        'CLASS': 'IS',
        'SIZE': 2,
        'OPCODE': '0150'
    },
    'START': {
        'CLASS': 'AD',
        'SIZE': 0,
        'OPCODE': '01'
    },
    'END': {
        'CLASS': 'AD',
        'SIZE': 0,
        'OPCODE': '02'
    },
    'LTORG': {
        'CLASS': 'AD',
        'SIZE': 0,
        'OPCODE': '03'
    },
    'DC': {
        'CLASS': 'DL',
        'SIZE': 0,
        'OPCODE': '01'
    },
    'DS': {
        'CLASS': 'DL',
        'SIZE': 0,
        'OPCODE': '02'
    }
}

REGISTER_TABLE = {
    'AREG': '1',
    'BREG': '2',
    'CREG': '3',
    'DREG': '4'
}