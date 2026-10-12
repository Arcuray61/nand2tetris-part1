# Nand2Tetris — Part I

My implementations of projects 1–6 from Nand2Tetris.
This repository follows the construction of the Hack computer,
from basic logic gates to a working assembler.

## Projects

| Folder | Project | Content |
|--------|---------|---------|
| projects/1 | Boolean Logic | Basic logic gates and multiplexers |
| projects/2 | Boolean Arithmetic | Adders and the ALU |
| projects/3 | Sequential Logic | Registers, RAM and the program counter |
| projects/4 | Machine Language | Multiplication and screen-fill programs |
| projects/5 | Computer Architecture | CPU, memory and the Hack computer |
| projects/6 | Assembler | A Hack assembler written in Java |

## Java Assembler

The assembler uses two passes to resolve labels and translate
Hack assembly into 16-bit machine code.

Features:
- A-instruction and C-instruction translation
- Predefined symbols, labels and variables
- Comment and whitespace handling
- C-instruction syntax and mnemonic validation
- A file chooser for selecting an .asm file
- Output saved as a .hack file in the input file's directory

### Running

Open the Java source files in projects/6/src in a Java IDE
with a configured JDK, then run the main method in main.java.

Select an .asm file in the file chooser.
The generated .hack file can be loaded into the Nand2Tetris CPU Emulator.

## Hardware Tests

Use the Nand2Tetris Hardware Simulator to run the supplied
.tst scripts and compare the results against the .cmp files.

The Nand2Tetris tools must be downloaded separately.

## Credits

Course materials, starter files and supplied tests belong to
the Nand2Tetris authors. My implementations are included here
as a record of my learning.

Course website: https://www.nand2tetris.org/
