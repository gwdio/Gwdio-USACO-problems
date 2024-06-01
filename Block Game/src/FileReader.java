import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileReader {
    private File in;
    private char[][][] data;
    private int len;
    public FileReader(File file) throws FileNotFoundException {
        in = file;
        Scanner reader = new Scanner(in);
        len = reader.nextInt();
        reader.nextLine();
        data = new char[2][len][];
        String[] lineParts;
        for (int i = 0; i < len; i++) {
            lineParts = reader.nextLine().toLowerCase().strip().split(" ");
            data[0][i] = lineParts[0].toCharArray();
            data[1][i] = lineParts[1].toCharArray();
        }
        reader.close();
    }
    public char[][][] getData() {
        return data;
    }
    public char[][] getData(int side) {
        return data[side];
    }

    public int getLen() {
        return len;
    }
}