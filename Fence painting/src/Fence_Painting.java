import java.io.*;
import java.util.Scanner;

public class Fence_Painting {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("src/paint.in");
        Scanner reader = new Scanner(in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        int c = reader.nextInt();
        int d = reader.nextInt();
        int result;
        reader.close();
        if (c <= b && d >= a) {
            result = Math.max(b,d) - Math.min(a,c);
        } else {
            result = (b-a)+(d-c);
        }
        try (FileWriter writer = new FileWriter
                ("paint.out",false)) {
            writer.write(Character.forDigit(result,10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}