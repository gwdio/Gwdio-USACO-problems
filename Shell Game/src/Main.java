import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;
import java.io.File;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GameFile Game = new GameFile();
        ArrayList<Pair> data = Game.readLine();
        Shell a = new Shell(1);
        Shell b = new Shell(2);
        Shell c = new Shell(3);
        for(Pair current: data){
            a.swapCheckCount(current);
            b.swapCheckCount(current);
            c.swapCheckCount(current);
        }
        int ret = Math.max(a.getCorrect(), Math.max(b.getCorrect(), c.getCorrect()));
        try {
            File file = new File("shell.out");
            FileWriter writer = new FileWriter(file);
            writer.write(Integer.toString(ret));
            writer.close();
            //System.out.println("File created: " + file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

    }

}