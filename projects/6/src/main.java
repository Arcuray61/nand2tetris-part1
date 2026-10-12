import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.*;
import javax.swing.JFileChooser;

public class main {
    public static void main(String[] args) throws IOException {
        String filePath;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }else{
            return;
        }
        if(!filePath.endsWith(".asm")){
            System.out.println("Geçersiz dosya türü. Lütfen .asm uzantılı bir dosya seçin.");
            return;
        }
        Parser parser = new Parser(filePath);
        Code code = new Code();
        SymbolTable symbolTable = new SymbolTable();
        Integer instructionCounter = 0;
        int symbolTableCounter = 16;

        ArrayList<String> asmToHack = new ArrayList<>();

        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.commandType().equals("C_COMMAND")) {
                instructionCounter++;

            } else if (parser.commandType().equals("A_COMMAND")) {
                instructionCounter++;
                } else if (parser.commandType().equals("L_COMMAND")) {
                    symbolTable.addEntry(parser.symbol(), instructionCounter);
                } else {
                    throw new IllegalArgumentException();
                }
        }

        Parser secondParser = new Parser(filePath);

        while (secondParser.hasMoreCommands()) {
            secondParser.advance();



            if (secondParser.commandType().equals("C_COMMAND")) {
                secondParser.validateCCommand();

                String compBits = code.comp(secondParser.comp());
                String destBits = code.dest(secondParser.dest());
                String jumpBits = code.jump(secondParser.jump());

                String machineCode = "111" + compBits + destBits + jumpBits;
                asmToHack.add(machineCode);

                System.out.println(secondParser.getCurrentCommand() + " -> " + machineCode);

            } else if (secondParser.commandType().equals("A_COMMAND")) {
                String machineCode = "0";
                int value;

                if(secondParser.symbol().matches("[0-9]+")){
                    value = Integer.parseInt(secondParser.symbol());

                }else if (symbolTable.contains(secondParser.symbol())){
                       value = symbolTable.getAddress(secondParser.symbol());
                    } else {
                    value = symbolTableCounter;
                    symbolTable.addEntry(secondParser.symbol(), symbolTableCounter);
                    symbolTableCounter++;
                }

                if (value >= 0 && value < 32768) {
                    String binary = Integer.toBinaryString(value);

                    int neededZero = 15 - binary.length();
                    machineCode += "0".repeat(neededZero) + binary;

                    asmToHack.add(machineCode);

                    System.out.println(secondParser.getCurrentCommand() + " -> " + machineCode);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }
        Files.write(Path.of(filePath.substring(0,filePath.lastIndexOf(".")) + ".hack"), asmToHack);
    }
}
