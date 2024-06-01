import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HashWriter {
    public static void writeHashMapToFile(HashMap<Character, Integer> alpha) throws IOException, IllegalAccessException {
        File out = new File("C:\\Users\\gwdio\\IdeaProjects\\Block Game\\src\\blocks.out");
        if (!out.canWrite()) {
            throw new IllegalAccessException("Cannot write to output");
        }
        FileWriter writer = new FileWriter(out);
        BufferedWriter bWriter = new BufferedWriter(writer);
        for (char c = 'a'; c <= 'z'; c++) {
            bWriter.write(c + ": " + alpha.getOrDefault(c, 0) + "\n");
//            System.out.print(c + ": " + alpha.getOrDefault(c, 0) + "\n"); //debugging purposes
        }
        bWriter.close();
    }
}
