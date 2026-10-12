// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.

//// Replace this comment with your code.

@sum
M = 0  // RAM[sum] = 0
@R0
D = M
@loop
M = D 

@R0
D = M  // D = RAM[R0]
@sum
D ; JEQ  

@R1
D = M
@sum
D ; JEQ

@0
D = A

(loop)
@R1
D = D + M  // D = D + RAM[R1]
@sum
M = D

@loop
M = M - 1
M ; JGT

(sum)
@sum
D = M // D = sum
@R2
M = D // R2 = sum

(end)
@end
0 ; JMP