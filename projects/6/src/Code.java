import java.util.*;

public class Code {
    Map<String, String> compTable = new HashMap<>();
    Set<String> destControl;

    public Code(){
        compTable.put("0","0101010");
        compTable.put("1","0111111");
        compTable.put("-1","0111010");
        compTable.put("D","0001100");
        compTable.put("A","0110000");
        compTable.put("M","1110000");
        compTable.put("!D","0001101");
        compTable.put("!A","0110001");
        compTable.put("!M","1110001");
        compTable.put("-D","0001111");
        compTable.put("-A","0110011");
        compTable.put("-M","1110011");
        compTable.put("D+1","0011111");
        compTable.put("A+1","0110111");
        compTable.put("M+1","1110111");
        compTable.put("D-1","0001110");
        compTable.put("A-1","0110010");
        compTable.put("M-1","1110010");
        compTable.put("D+A","0000010");
        compTable.put("D+M","1000010");
        compTable.put("D-A","0010011");
        compTable.put("D-M","1010011");
        compTable.put("A-D","0000111");
        compTable.put("M-D","1000111");
        compTable.put("D&A","0000000");
        compTable.put("D&M","1000000");
        compTable.put("D|A","0010101");
        compTable.put("D|M","1010101");
        destControl = new HashSet<>();
        destControl.add("");
        destControl.add("D");
        destControl.add("M");
        destControl.add("MD");
        destControl.add("A");
        destControl.add("AM");
        destControl.add("AD");
        destControl.add("AMD");
    }

    public String dest(String mnemonic){

        if(!destControl.contains(mnemonic)){
            throw new IllegalArgumentException();
        }

        char[] destCode = {'0','0','0'};

        if(mnemonic.contains("A")){
           destCode[0] = '1';
        }
        if(mnemonic.contains("D")){
            destCode[1] = '1';
        }
        if(mnemonic.contains("M")){
            destCode[2] = '1';
        }

        return new String(destCode);
    }

    public String jump(String mnemonic){
        String jumpCode = "000";
        if(mnemonic.equals("JGT")){
            jumpCode = "001";
            return jumpCode;
        }
        if(mnemonic.equals("JEQ")){
            jumpCode = "010";
            return jumpCode;
        }
        if(mnemonic.equals("JGE")){
            jumpCode = "011";
            return jumpCode;
        }
        if(mnemonic.equals("JLT")){
            jumpCode = "100";
            return jumpCode;
        }
        if(mnemonic.equals("JNE")){
            jumpCode = "101";
            return jumpCode;
        }
        if(mnemonic.equals("JLE")){
            jumpCode = "110";
            return jumpCode;
        }
        if(mnemonic.equals("JMP")){
            jumpCode = "111";
            return jumpCode;
        }
        if(mnemonic.isEmpty()){
            return jumpCode;
        }


        throw  new IllegalArgumentException();
    }

    public String comp(String mnemonic){

        String bits = compTable.get(mnemonic);

        if(bits==null){
            throw new IllegalArgumentException();
        }
        else{
            return bits;
        }
    }
}
