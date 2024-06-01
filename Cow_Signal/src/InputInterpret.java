import java.util.Scanner;
public class InputInterpret {
    private int a, b, c;
    private String[] line;
    private char[][] icon;
    public InputInterpret() {
        Scanner reader = new Scanner(System.in);
        line = reader.nextLine().strip().split(" ");
        assert line.length == 3;
        this.a = Integer.parseInt(line[0]);
        this.b = Integer.parseInt(line[1]);
        this.c = Integer.parseInt(line[2]);

        icon = new char[this.a][];
        for (int i = 0; i < a; i++) {
            String row = reader.nextLine();
            icon[i] = row.toCharArray();
        }
        reader.close();
    }

    public int getA() {
        return this.a;
    }
    public int getB() {
        return this.b;
    }
    public int getC() {
        return this.c;
    }
    public char[][] getIcon() {
        return this.icon;
    }
    public char[] getIconRow(int rowIndex) {
        return this.icon[rowIndex];
    }
    public char getIconChar(int rowIndex, int colIndex) {
        return this.icon[rowIndex][colIndex];
    }
}