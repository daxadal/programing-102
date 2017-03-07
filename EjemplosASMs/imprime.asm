; Codigo ASM para TPMV
; Tecnología de la Programación - FDI (UCM)
; Marco Antonio Gómez Martín
push 1234

; Escribe un entero
; Recibe en la cima de la pila el número a escribir.
; Utiliza la posición 100 de memoria para guardar
; una variable auxiliar
;
;  if (a == 0)
;     print '0';
;  else {
;     aux = 0;
;     while (a != 0) {
;         aux *= 10;
;         aux += (a % 10);
;         a /= 10;
;     }
;     while (aux != 0) {
;         print '0' + (aux%10);
;         aux /= 10;
;     }
;  }
;

; if (a == 0)
dup
rbt 5
; print '0';
push 48
out
pop
rjump 40
; else {
;    aux = 0;
push 0
store 100
;    while (a != 0)
dup
rbt 2
rjump 16
;      aux *= 10; aux += (a % 10)
dup
dup
push 10
div
push 10
mul
sub
load 100
push 10
mul
add
store 100
; a /= 10
push 10
div
; }
rjump -17

; 
pop
load 100
; while (aux != 0) {
dup
rbt 2
rjump 15
;   print aux%10
dup
dup
push 10
div
push 10
mul
sub
push 48
add
out
; aux /= 10
push 10
div
; }
rjump -15
pop
; FIN
push 10
out
push 13
out
