; Codigo ASM para TPMV
; Tecnología de la Programación - FDI (UCM)
; Marco Antonio Gómez Martín
;
; Pide al usuario que teclee un número y lo escribe
; por pantalla.
;
; El código es generado por un pequeño compilador de terceros.
;
JUMP 1
PUSH 3
STORE 1
PUSH 5
STORE 0
JUMP 325
LOAD 0
PUSH 2
ADD
LOAD 2
STOREIND
LOAD 0
PUSH 3
ADD
STORE 2
LOAD 0
PUSH 4
ADD
STORE 0
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 0
eq
not
BF 70
LOAD 0
PUSH 1
ADD
PUSH 45
STOREIND
LOAD 0
PUSH 3
ADD
DUP
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 10
DIV
STOREIND
POP
JUMP 6
LOAD 2
PUSH 1
ADD
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 10
DIV
STOREIND
PUSH 48
LOAD 2
PUSH 0
ADD
LOADIND
ADD
PUSH 10
LOAD 2
PUSH 1
ADD
LOADIND
MUL
SUB
OUT
JUMP 70
LOAD 2
PUSH 2
SUB
LOADIND
LOAD 2
PUSH 3
SUB
DUP
STORE 0
PUSH 2
ADD
LOADIND
STORE 2
JUMPIND
LOAD 0
PUSH 2
ADD
LOAD 2
STOREIND
LOAD 0
PUSH 3
ADD
STORE 2
LOAD 0
PUSH 3
ADD
STORE 0
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 0
EQ
BF 109
PUSH 48
PUSH 0
ADD
OUT
JUMP 125
LOAD 0
PUSH 1
ADD
PUSH 125
STOREIND
LOAD 0
PUSH 3
ADD
DUP
LOAD 2
PUSH 0
ADD
LOADIND
STOREIND
POP
JUMP 6
LOAD 2
PUSH 2
SUB
LOADIND
LOAD 2
PUSH 3
SUB
DUP
STORE 0
PUSH 2
ADD
LOADIND
STORE 2
JUMPIND
LOAD 0
PUSH 2
ADD
LOAD 2
STOREIND
LOAD 0
PUSH 3
ADD
STORE 2
LOAD 0
PUSH 2
ADD
STORE 0
PUSH 10
OUT
PUSH 13
OUT
LOAD 2
PUSH 2
SUB
LOADIND
LOAD 2
PUSH 3
SUB
DUP
STORE 0
PUSH 2
ADD
LOADIND
STORE 2
JUMPIND
LOAD 0
PUSH 2
ADD
LOAD 2
STOREIND
LOAD 0
PUSH 3
ADD
STORE 2
LOAD 0
PUSH 3
ADD
STORE 0
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 48
LT
DUP
BT 199
POP
LOAD 2
PUSH 0
ADD
LOADIND
PUSH 59
flip
LE
BF 206
LOAD 1
PUSH 0
ADD
PUSH 0
STOREIND
JUMP 211
LOAD 1
PUSH 0
ADD
PUSH 1
STOREIND
LOAD 2
PUSH 2
SUB
LOADIND
LOAD 2
PUSH 3
SUB
DUP
STORE 0
PUSH 2
ADD
LOADIND
STORE 2
JUMPIND
LOAD 0
PUSH 2
ADD
LOAD 2
STOREIND
LOAD 0
PUSH 3
ADD
STORE 2
LOAD 0
PUSH 4
ADD
STORE 0
LOAD 1
PUSH 1
ADD
PUSH 0
STOREIND
LOAD 2
PUSH 0
ADD
IN
STOREIND
LOAD 2
PUSH 1
ADD
PUSH 0
STOREIND
LOAD 2
PUSH 1
ADD
LOADIND
NOT
BF 311
LOAD 0
PUSH 1
ADD
PUSH 275
STOREIND
LOAD 0
PUSH 3
ADD
DUP
LOAD 2
PUSH 0
ADD
LOADIND
STOREIND
POP
JUMP 170
LOAD 1
PUSH 0
ADD
LOADIND
PUSH 1
EQ
BF 305
LOAD 1
PUSH 1
ADD
PUSH 10
LOAD 1
PUSH 1
ADD
LOADIND
MUL
LOAD 2
PUSH 0
ADD
LOADIND
ADD
PUSH 48
SUB
STOREIND
LOAD 2
PUSH 0
ADD
IN
STOREIND
JUMP 310
LOAD 2
PUSH 1
ADD
PUSH 1
STOREIND
JUMP 253
LOAD 2
PUSH 2
SUB
LOADIND
LOAD 2
PUSH 3
SUB
DUP
STORE 0
PUSH 2
ADD
LOADIND
STORE 2
JUMPIND
LOAD 0
PUSH 1
ADD
PUSH 331
STOREIND
JUMP 225
LOAD 0
PUSH 1
ADD
PUSH 347
STOREIND
LOAD 0
PUSH 3
ADD
DUP
LOAD 1
PUSH 1
ADD
LOADIND
STOREIND
POP
JUMP 84
LOAD 0
PUSH 1
ADD
PUSH 353
STOREIND
JUMP 139
HALT