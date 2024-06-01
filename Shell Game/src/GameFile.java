import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class GameFile {
    File file;
    public GameFile(){
        file = new File("shell.in");
    }
    public File getFile(){
        return this.file;
    }
    public ArrayList readLine() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int size = Integer.parseInt(reader.nextLine());
        ArrayList<Pair> ret = new ArrayList<>(size);
        while(reader.hasNextLine()){
            ret.add(Pair.parseFromString(reader.nextLine()));
        }
        reader.close();
        return ret;
    }

}
