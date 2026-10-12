(MAINLOOP)
	@SCREEN
	D = A
	@i
	M = D
	
	@KBD
	D = M 
	@LOOP
	D ; JNE
	@LOOPWHITE
	D ; JEQ

	//Turn Screen On
	(LOOP)
		@i
		A = M
		M = -1

		@i
		M = M + 1

		@KBD
		D = A
		@i
		D = D - M

		@LOOP
		D ; JGT
	@MAINLOOP
	0;JMP
	// Turn Screen Off
	(LOOPWHITE)
		@i
		A = M
		M = 0

		@i
		M = M + 1

		@KBD
		D = A
		@i
		D = D - M

		@LOOPWHITE
		D ; JGT

@MAINLOOP
0 ; JMP



