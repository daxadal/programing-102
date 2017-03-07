; Mini programa que lee toda la entrada y la vuelve
; a escribir eliminando todas las aes minúsculas.
; En C++ sería algo así:
;
; while (cin) {
;     char c;
;     cin << c;
;     if (cin && (c != 'a'))
;        cout >> c;
; }
;

; Leemos el caracter de la entrada
in
; Miramos si hemos llegado al final
dup      ; Duplicamos el valor devuelto por IN
push -1  ; Si es == -1, entonces
eq       ; hemos llegado al final de la entrada
bt 11    ; por lo que saltamos al final del programa
; No hemos llegado al final. Miramos si es una 'a'
dup
push 97  ; ascii('a') = 97
eq
bt 0
; No es una 'a'. Escribimos y volvemos a empezar
out
lkdjump 12 ; **************Añadido propio********
jump 0 
;
; Fin del programa.
; Número de instrucciones = 11
; 
write 0 0
write 1 1
lkdjump 17
write 2 2
return
write 3 3
return

