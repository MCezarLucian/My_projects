.586
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern printf: proc
extern scanf: proc
extern fopen: proc
extern fclose: proc
extern fprintf: proc
extern fscanf: proc

extern exit: proc

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data

citesc db "r",0 
scriu db "w",0

fisier_out db "out.txt",0

r1 db "Rezultat:",0 ;mesaj pentru apelant
aux dd 0
var1 dd 0

format db "%d",0 ;format intreg fara spatiu
format2 db "%d ",0;format intreg cu spatiu
format_sir db "%s",0 ; format siruri de caractere
semn db 0
eroare_det db "Nu se poate face determinant",0 ; mesaj eroare determinant
new_line db 10,0 ;sir linie noua

matrice db 0,0,0
		db 0,0,0
		db 0,0,0
matrice1 db 0,0,0
		db 0,0,0
		db 0,0,0
matrice2 db 1,2,3
		 db 4,5,6
		 db 7,8,9
mat_rezultat db 0,0,0
			 db 0,0,0
			 db 0,0,0
var2 dd	 0 ; variabila ajutatoare
var3 dd 0; variabila ajutatoare

dim dd 3 ;dimensiunea matricei
dim2 dd 6 ;dimensiune ajutatoare

p1 dd 0 ; variabila ajutatoare
p2 dd 0 ; variabila ajutatoare

scalar db 3 ;scalarul

rez1 dd 0 ; variabila ajutatoare
rez2 dd 0 ; variabila ajutatoare

det_dim dd 0 ;variabila determinant
det_plus dd 0 ;variabila determinant
det_minus dd 0 ;variabila determinant

aegal db "A=",0 ;mesaj pentru apelant
begal db "B=",0 ;mesaj pentru apelant
atext db "     ",0 ;fisierul din care se va citi prima matrice
btext db "     ",0 ;fisierul din care se va citi a doua matrice
rezultattxt db "            ",0 ;fisierul in care se va afisa matricea rezultat
scalafis db "Scalar=",0 ;mesaj apelant
.code
; PROCEDURA PENTRU ADUNAREA A DOUA MATRICE
adunare_matrici proc
	push ebx
	push ecx
	mov ecx,dim
	dec ecx
	mov eax,dim
	mul ecx
	mov dim2,eax ;calculam dimensiunea liniilor
	mov ebx,0 ;contor linii
	mov esi,0
	for1:
		mov esi,0 ;contor coloane
		for2:
			mov ecx,0
			;facem suma in ecx dupa care o salvam in matricea rezultat
			add cl,matrice[ebx][esi]
			add cl,matrice2[ebx][esi]
			add mat_rezultat[ebx][esi],cl
			inc esi
			cmp esi,dim ;verificam daca am ajuns la ultimul elemnt de e linia respectiva
			je cfor1
			jmp for2
		cfor1:
		cmp ebx,dim2; verificam daca am ajuns la ultimul element din matrice
		je sfarsit
		add ebx,dim
		jmp for1
	sfarsit:
	pop ebx
	pop ecx
	ret
adunare_matrici endp

scadere_matrici proc
	push ebx
	push ecx
	mov ecx,dim
	dec ecx
	mov eax,dim
	mul ecx
	mov dim2,eax ;calculam dimensiunea liniilor
	mov ebx,0 ;contor linii
	mov esi,0
	for1:
		mov esi,0 ;contor coloane
		for2:
			mov ecx,0
			;facem diferenta in ecx dupa care o salvam in matricea rezultat
			add cl,matrice[ebx][esi]
			sub cl,matrice2[ebx][esi]
			add mat_rezultat[ebx][esi],cl
			inc esi
			cmp esi,dim ;verificam daca am ajuns la ultimul elemnt de e linia respectiva
			je cfor1
			jmp for2
		cfor1:
		cmp ebx,dim2; verificam daca am ajuns la ultimul element din matrice
		je sfarsit
		add ebx,dim
		jmp for1
	sfarsit:
	pop ebx
	pop ecx
	ret
scadere_matrici endp

; PROCEDURA PENTRU INMULTIREA UNEI MATRICE CU SCALAR
inmultire_scalar proc
	push ebx
	push ecx
	mov ecx,dim
	dec ecx
	mov eax,dim
	mul ecx
	mov dim2,eax
	mov ebx,0 ;contor linii 
	for1:
		mov esi,0 ;contor coloane
		for2:
			; ne vom salva cate un element in ecx dupa care le vom inmulti pe rand cu scalarul
			mov ecx,0
			add cl,matrice[ebx][esi]
			mov eax,ecx
			mul scalar ;eax=eax*scalar
			add mat_rezultat[ebx][esi],al
			inc esi
			cmp esi,dim ;verificam daca am ajuns la ultimul elemnt de e linia respectiva
			je cfor1
			jmp for2
		cfor1:
		cmp ebx,dim2 ; verificam daca am ajuns la ultimul element din matrice
		je sfarsit
		add ebx,dim
		jmp for1
	sfarsit:
	pop ebx
	pop ecx
	ret
inmultire_scalar endp

; FUNCTIE TRANSPUSA
transpusa proc
	push eax
	push ebx
	push ecx
	push edx
	mov ebx,0
	mov esi,0
	;transpusa merge pe ideea ca face i=linie/dim si j=coloana*dim si mat_rezultat[j][i]=matrice[i][j]
	for1:
		mov esi,0
		
		for2:
			mov eax,0
			mov ecx,0
			mov var1,ebx
			mov var2,esi
			mov cl,matrice[ebx][esi]
			mov aux,ecx
			mov eax,0
			mov ecx,0
			mov eax,ebx
			mov edx,0;golim edx ca sa putem face div
			div dim
			mov ecx,eax
			mov eax,esi
			mul dim
			mov edx,eax
			mov eax,aux
			mov mat_rezultat[edx][ecx],al
			mov ebx,var1
			mov esi,var2
			inc esi
			cmp esi,dim
			je cfor1
			jmp for2
		
		cfor1:
		cmp ebx,dim2
		je sfarsit
		add ebx,dim
		jmp for1
		
		
	sfarsit:
	pop eax
	pop ebx
	pop ecx
	pop edx
	ret
transpusa endp

; FUNCTIE DETERMINANT
determinant proc
	push ebx
	push offset scriu
	push offset rezultattxt
	call fopen
	mov p2,eax
	add esp,8
	; verificam ce dimensiune are matricea si in functie de aceasta facem determinantul corespunzator
	cmp dim,3
	je det_trei
	cmp dim,2
	je det_doi
	cmp dim,1
	je det_unu
	jmp eroare
	det_trei: ; algoritm determinant dupa regula lui Sarrus
		mov eax,0
		mov ebx,0
		mov al,matrice[0][0]
		mov bl,matrice[3][1]
		mul ebx
		mov bl,matrice[6][2]
		mul ebx
		mov det_plus,eax
		mov eax,0
		mov al,matrice[3][0]
		mov bl,matrice[6][1]
		mul ebx
		mov bl,matrice[0][2]
		mul ebx
		add det_plus,eax
		mov eax,0
		mov al,matrice[6][0]
		mov bl,matrice[0][1]
		mul ebx
		mov bl,matrice[3][2]
		mul ebx
		add det_plus,eax
		mov eax,0
		mov al,matrice[0][2]
		mov bl,matrice[3][1]
		mul ebx
		mov bl,matrice[6][0]
		mul ebx
		mov det_minus,eax
		mov eax,0
		mov al,matrice[3][2]
		mov bl,matrice[6][1]
		mul ebx
		mov bl,matrice[0][0]
		mul ebx
		add det_minus,eax
		mov eax,0
		mov al,matrice[6][2]
		mov bl,matrice[0][1]
		mul ebx
		mov bl,matrice[3][0]
		mul ebx
		add det_minus,eax
		mov ecx,0
		mov ecx,det_minus
		sub det_plus,ecx
		mov ebx,0
		mov eax,0
		; afisam determinantul
		push det_plus
		push offset format2
		push p2
		call fprintf
		add esp,12
		jmp sfarsit
	det_doi:
		mov eax,0
		mov ebx,0
		add al,matrice[0][0]
		mov bl,matrice[2][1]
		mul ebx
		mov det_plus,eax
		mov eax,0
		add al,matrice[0][1]
		mov bl,matrice[2][0]
		mul ebx
		mov det_minus,eax
		sub det_plus,eax
		;afisam determinantul
		push det_plus
		push offset format2
		push p2
		call fprintf
		add esp,12
		jmp sfarsit
	det_unu:
		mov bl,matrice[0][0]
		;afisam determinantul
		push ebx
		push offset format2
		push p2
		call fprintf
		add esp,12
		jmp sfarsit
	eroare:
		push offset eroare_det
		push offset format_sir
		push p2
		call fprintf
		add esp,12
	sfarsit:
determinant endp

; FUNCTIE CITIRE MATRICE 1
citire_a proc
	push ebx
	push ecx
	push offset citesc
	push offset atext
	call fopen
	mov p1,eax
	add esp,8
	push offset dim
	push offset format
	push p1
	call fscanf
	add esp,12
	mov ecx,dim
	dec ecx
	mov eax,dim
	mul ecx
	mov dim2,eax ;calculam dimensiunea liniilor
	mov ebx,0;contor linii
	mov esi,0; contor coloane
	loop1:
		mov esi,0
		loop2:
			mov var2,ebx; salvez registrele in caz ca se pot schimba cand apelam fscaf
			mov var3,esi
			;citim cate un elemt
			push offset var1
			push offset format
			push p1
			call fscanf
			add esp,12
			mov ebx,var2
			mov esi,var3
			mov ecx,var1
			mov matrice[ebx][esi],cl
			inc esi
			cmp esi,dim
			je cloop1
			jmp loop2
	cloop1:
	cmp ebx,6
	je sfarsit1
	add ebx,dim
	jmp loop1
	sfarsit1:
	;call fclose
	pop ebx
	pop ecx
	ret
citire_a endp

;FUNCTIE CITIRE MATRICE 2
citire_b proc
	push ebx
	push ecx
	push offset citesc
	push offset btext
	call fopen
	mov p1,eax
	add esp,8
	mov ebx,0;contor linii
	mov esi,0; contor coloane
	loop1:
		mov esi,0
		loop2:
			mov var2,ebx
			mov var3,esi
			push offset var1
			push offset format
			push p1
			call fscanf
			add esp,12
			mov ebx,var2
			mov esi,var3
			mov ecx,var1
			mov matrice2[ebx][esi],cl
			inc esi
			cmp esi,dim
			je cloop1
			jmp loop2
	cloop1:
	cmp ebx,dim2
	je sfarsit1
	add ebx,dim
	jmp loop1
	sfarsit1:
	;call fclose
	pop ebx
	pop ecx
	ret
citire_b endp

;FUNCTIE AFISARE MATRICE REZULTAT
afiseaza proc	
	push ebx
	push ecx
	push offset scriu
	push offset rezultattxt
	call fopen
	mov p2,eax
	add esp,8
	mov ebx,0 ;contor linie
	for1:
		mov esi,0;contor coloana
		for2:
			mov ecx,0
			mov cl,mat_rezultat[ebx][esi]
			mov var1,ecx
			mov var2,ebx
			mov var3,esi
			;afisam cate un element
			push var1
			push offset format2
			push p2
			call fprintf
			add esp,12
			mov ebx,var2
			mov esi,var3
			inc esi
			cmp esi,dim
			je linie_noua
			jmp for2
		cfor1:
		cmp ebx,dim2
		je sfarsit
		add ebx,dim
		jmp for1
		linie_noua:
		mov var2,ebx
		mov var3,esi
		push offset new_line
		push offset format_sir
		push p2
		call fprintf
		add esp,12
		mov ebx,var2
		mov esi,var3
		jmp cfor1
		sfarsit:
		;call fclose
		pop ebx
		pop ecx	
		ret
afiseaza endp



start:
	nop
	nop
	;citim mesajul de la tastatura
	push offset semn
	push offset format_sir
	call scanf
	add esp,8
	; testam sirul si in functie de ce a fost dat aplicam operatia
	cmp semn[1],43
	je adunare
	cmp semn[1],45
	je adunare
	cmp semn[0],97
	je scal
	cmp semn[1],101
	je det
	cmp semn[1],84
	je tr
	;jmp err
	; loop de adunare
	adunare:
		push offset aegal ;afisam A=
		push offset format_sir
		call printf
		add esp,8
		push offset atext ;aici vom salva fisierul din care citim matricea1
		push offset format_sir
		call scanf
		add esp,8
		push offset begal; afisam B=
		push offset format_sir
		call printf
		add esp,8
		push offset btext; aici vom salva fisierul din care citim matricea2
		push offset format_sir
		call scanf
		add esp,8
		;apelam functiile de citire a matricelor
		call citire_a
		call citire_b
		cmp semn[1],45
		je sg
		;apelam functia de adunare a matricelor
		call adunare_matrici
		jmp rezult
		sg:
		call scadere_matrici
		jmp rezult
	
	scal:
		push offset aegal ; afisam A=
		push offset format_sir
		call printf
		add esp,8
		push offset atext ;aici vom salva fisierul din care citim matricea 
		push offset format_sir
		call scanf
		add esp,8
		push offset scalafis; afisam Scalar=
		push offset format_sir
		call printf
		add esp,8
		push offset scalar ;aici salvam valoarea citita a scalarului
		push offset format
		call scanf
		add esp,8
		call citire_a ;citim matricea
		call inmultire_scalar ;facem inmultirea cu scalar
		jmp rezult
	
	det:
		push offset aegal ; afisam A=
		push offset format_sir
		call printf
		add esp,8
		push offset atext ;aici vom salva fisierul din care citim matricea
		push offset format_sir
		call scanf
		add esp,8
		push offset r1 ; afisam Rezultat:
		push offset format_sir
		call printf
		add esp,8
		push offset rezultattxt ;aici salvam numele fisierului in care vom afisa rezultatul
		push offset format_sir
		call scanf
		add esp,8
		call citire_a ;citim matricea
		call determinant ;facem determinantul
		jmp final
	tr:
		push offset aegal ;afisam A=
		push offset format_sir
		call printf
		add esp,8
		push offset atext ;aici vom salva fisierul din care citim matricea1
		push offset format_sir
		call scanf
		add esp,8
		call citire_a
		call transpusa
		jmp rezult
	rezult:
		push offset r1 ; afisam Rezultat:
		push offset format_sir
		call printf
		add esp,8
		push offset rezultattxt; aici salvam numele fisierului in care afisam matricea rezultat
		push offset format_sir
		call scanf
		add esp,8
		call afiseaza;afisam
		jmp final
	final:
	push 0
	call exit
end start
