import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        File in = new File("src/billboard.in");
        Scanner reader = new Scanner(in);
        @SuppressWarnings("SpellCheckingInspection")
        Rect[] rects = new Rect[3]; // Array to store the pairs
        for (int i = 0; i < rects.length; i++) {
            int x1 = reader.nextInt();
            int y1 = reader.nextInt();
            int x2 = reader.nextInt();
            int y2 = reader.nextInt();
            System.out.println("pair index "+i+" read into data"); //debugging
            if(reader.hasNext()) reader.nextLine();
            rects[i] = new Rect(new Coordinate(x1, y1), new Coordinate(x2, y2));
        }
        reader.close();
        int area1 = rects[0].area()-rects[0].calcOverlap(rects[2]);
        int area2 = rects[1].area()-rects[1].calcOverlap(rects[2]);
        int visible = area1+area2;
        System.out.println("Final results, a1, a2, total"); //debugging
        System.out.println(area1+" + "+area2+" = "+visible); //debugging
        FileWriter writer = new FileWriter("src/billboard.out",false);
        writer.write(Integer.toString(visible,10));
        writer.close();
    }
}