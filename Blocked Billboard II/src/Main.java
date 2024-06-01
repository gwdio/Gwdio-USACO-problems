import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InvalidRectangleException {
        File input = new File("src/billboard.in");
        Rectangle good;
        Rectangle bad;
        // Assign the returned rectangles to the variables
        bad = readData(input).get(0);
        good = readData(input).get(1);
        int countB = 0;
        for (Coordinate c : bad.getCorners()) {
            if (good.containsCoordinate(c))
                countB++;
        }
        int area;
        area = switch (countB){
            case 0, 1 -> bad.area();
            case 2 -> {
                int side = bad.findSideInside(good);
                int sideLength = bad.getDimensions()[wrap(side,1)]-bad.getDimensions()[wrap(side,3)];
                int modifiedSide = good.getDimensions()[wrap(side,2)]-bad.getDimensions()[side];
                yield Math.abs(sideLength*modifiedSide);
            }
            case 4 -> 0;
            default -> throw new IllegalArgumentException();
        };
        FileWriter writer = new FileWriter("src/billboard.out",false);
        writer.write(Integer.toString(area,10));
        writer.close();
    }
    public static int wrap(int value, int shift){
        return (value+shift)%4;
    }
    public static List<Rectangle> readData(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        List<Rectangle> rectangles = new ArrayList<>();

        // Read coordinates for the "good" rectangle
        int goodX1 = scanner.nextInt();
        int goodY1 = scanner.nextInt();
        int goodX2 = scanner.nextInt();
        int goodY2 = scanner.nextInt();
        Coordinate goodTopLeft = new Coordinate(goodX1, goodY1);
        Coordinate goodBottomRight = new Coordinate(goodX2, goodY2);
        Rectangle good = new Rectangle(goodTopLeft, goodBottomRight);
        scanner.nextLine(); // Consume the newline character

        // Read coordinates for the "bad" rectangle
        int badX1 = scanner.nextInt();
        int badY1 = scanner.nextInt();
        int badX2 = scanner.nextInt();
        int badY2 = scanner.nextInt();
        Coordinate badTopLeft = new Coordinate(badX1, badY1);
        Coordinate badBottomRight = new Coordinate(badX2, badY2);
        Rectangle bad = new Rectangle(badTopLeft, badBottomRight);

        rectangles.add(good);
        rectangles.add(bad);

        scanner.close();
        return rectangles;
    }
}
