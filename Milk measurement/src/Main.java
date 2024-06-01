import java.io.*;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        int changes = 0;
        File input = new File("src/measurement.in");
        FileReader raw = new FileReader(input);
        Leaderboard current = new Leaderboard();
        Leaderboard past = new Leaderboard();
        for (int i = 0; i < raw.getLen(); i++) {
            past.pastAndFuture(current);
            current.updateMilk(raw.getSubarray(i));
            current.updateTop();
            System.out.println(Arrays.toString(current.getMilk()));
            System.out.println(Arrays.toString(current.getTop()));
            System.out.println(changes);
            if (current.compareToPast(past)){
                changes++;
                System.out.println("update");
                System.out.println(Arrays.toString(past.getTop())+" -> "+Arrays.toString(current.getTop()));
            }
        }
        input = null;
        raw = null;
        current =null;
        past = null;
        System.out.println(changes);
        writeToFile(changes);
    }
    public static void writeToFile(int data) throws IOException, IllegalAccessException {
        File output = new File("C:\\Users\\gwdio\\IdeaProjects\\Milk measurement\\src\\measurement.out");
        if (!output.canWrite()){
            throw new IllegalAccessException("cannot write to output");
        }
        FileWriter writer = new FileWriter(output);
        BufferedWriter bWriter = new BufferedWriter(writer);
        bWriter.write(Integer.toString(data));
        bWriter.close();
    }
}