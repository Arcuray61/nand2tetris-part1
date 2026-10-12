import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser {

    List<String> lines;
    int index = 0;
    String currentCommand;

    public Parser(String filePath) throws IOException {
        lines = Files.readAllLines(Path.of(filePath));
    }

    private String cleanLine(String line){
        int commentIndex = line.indexOf("//");
        if(commentIndex>=0){
            line = line.substring(0, commentIndex);
        }
        line = line.replaceAll("\\s+", "");

        return line;
    }

    public boolean hasMoreCommands(){
        for(int i = index; i<lines.size();i++){
            String cleanedLine = cleanLine(lines.get(i));
            if(!cleanedLine.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public void advance(){
        while(index < lines.size()){
            String line = lines.get(index);
            index++;
            line = cleanLine(line);
            if(!line.isEmpty()){
               currentCommand = line;
               return;
            }
        }
    }

    public String getCurrentCommand(){
        return currentCommand;
    }

    public String commandType(){
        if(getCurrentCommand().startsWith("@")){
            return "A_COMMAND";
        }
        else if(getCurrentCommand().startsWith("(") && getCurrentCommand().endsWith(")")){
            return "L_COMMAND";
        }
        else{
            return "C_COMMAND";
        }
    }

    public String symbol(){
        if(commandType().equals("A_COMMAND")){
           return getCurrentCommand().substring(1);
        }
        else if(commandType().equals("L_COMMAND")){
            return getCurrentCommand().substring(1,getCurrentCommand().length()-1);
        }
        else{
            throw new IllegalStateException("symbol() yalnız A ve L komutlarında kullanılır.");
        }
    }

    public String dest(){
        if(commandType().equals("C_COMMAND")){
            int index = getCurrentCommand().indexOf("=");
            if(index==-1){
                return "";
            }
            else {
                return getCurrentCommand().substring(0, index);
            }
        }
        else{
            return "";
        }
    }
    public String comp(){
        if(commandType().equals("C_COMMAND")){
            int start;
            int end;
            if(getCurrentCommand().contains("=")){
                start = getCurrentCommand().indexOf("=") + 1;
            } else{
                start = 0;
            }
            if(getCurrentCommand().contains(";")){
                end = getCurrentCommand().indexOf(";");
            } else{
                end = getCurrentCommand().length();
            }

            return getCurrentCommand().substring(start, end);
        }
        else{
            return "";
        }
    }
    public String jump(){
        if(commandType().equals("C_COMMAND")){
            int index = getCurrentCommand().indexOf(";");
            if(index==-1){
                return "";
            } else{
                return getCurrentCommand().substring(index+1);
            }
        }
        else{
            return "";
        }
    }

    public void validateCCommand(){
        if(!(currentCommand.indexOf("=") == currentCommand.lastIndexOf("="))){
            throw new IllegalArgumentException();
        }
        if(!(currentCommand.indexOf(";") == currentCommand.lastIndexOf(";"))){
            throw new IllegalArgumentException();
        }
        else if(currentCommand.contains("=") && currentCommand.contains(";") && currentCommand.indexOf("=")>currentCommand.indexOf(";")){
            throw new IllegalArgumentException();
        }
        else if(comp().isEmpty()){
            throw new IllegalArgumentException();
        }
        else if(currentCommand.contains("=") && dest().isEmpty()){
            throw new IllegalArgumentException();
        }
        else if(currentCommand.contains(";") && jump().isEmpty()){
            throw new IllegalArgumentException();
        }
    }
}
