import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class FileReader {
    private File in;
    private String[][] data;
    private int len;
    public FileReader(File file) throws FileNotFoundException {
        in = file;
        Scanner reader = new Scanner(in);
        len=reader.nextInt();
        reader.nextLine();
        data = new String[len][3];
        for (int i = 0; i < len && reader.hasNextLine(/*"\\d+ \\w+ [+-]\\d+"*/); i++) {
            //regex that currently not using
            data[i] = reader.nextLine().trim().split(" ");
            System.out.println(Arrays.toString(data[i]));
            assert data[i].length==3;
        }
        reader.close();
        Arrays.sort(data, Comparator.comparingInt(arr -> Integer.parseInt(arr[0])));
    }
    public String getElement(int row, int col) {
        if (row >= 0 && row < len && col >= 0 && col < 3) {
            return data[row][col];
        } else {
            throw new IllegalArgumentException("Invalid row or column index.");
        }
    }
    public String[][] getData() {
        return data;
    }
    public String[] getSubarray(int row) {
        if (row >= 0 && row < len) {
            return data[row];
        } else {
            throw new IllegalArgumentException("Invalid row index.");
        }
    }
    public int getLen() {
        return len;
    }
}
