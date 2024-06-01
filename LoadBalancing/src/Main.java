import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/balancing.in");
        Scanner scanner = new Scanner(file);

        // Read N and B from the first line
        final int N = scanner.nextInt();
        final int B = scanner.nextInt();
        scanner.nextLine(); // Move to the next line
        // Create an array of Point objects
        Point[] points = new Point[N];
        // Read the following lines and store them as Point objects
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
            if(scanner.hasNext()) scanner.nextLine(); // Move to the next line
        }
        // Close the scanner
        scanner.close();

        int x = ((B/2)&~1);
        int y = x;
        int shift = (int) (Math.log(B)/Math.log(2));
        int b = Integer.MAX_VALUE;
        int d;
        int[] values = new int[4];
        double dFactor=1;
        for (int i = 0; i < B; i++) {
            Point diff = pointDiff(points,x,y);
            int xDiff = diff.getX();
            int yDiff = diff.getY();
            d = maxDiff(balance(points, x, y));
            values[i%4] = d;
            b = Math.min(b, d);


            System.out.println(Arrays.toString(balance(points,x,y)));
            System.out.println("balancing: "+b);

            //checks if 0 = 2
            if (values[i%4]==values[(i+2)%4]){
                //checks if 0=1 or 1=3
                if (values[(i+1)%4]==values[(i+3)%4]||values[i%4]==values[(i+1)%4]){
                    System.out.println("Break by: " + Arrays.toString(values));
                    break;
                }
            }
            //adjustment operation
            x += ((((xDiff >> 31) == 0) ? shift : -shift) + (int)(xDiff*dFactor))*2;
            y += ((((yDiff >> 31) == 0) ? shift : -shift) + (int)(yDiff*dFactor))*2;
            shift = (int)Math.max(shift/1.5,1);
            if(shift==1) dFactor = Math.max((dFactor-Math.sqrt(N)/N),0);
            System.out.println("center: ("+x+","+y+") shift: "+shift+" dFactor: "+dFactor);
        }
        System.out.println("final result: "+ b);
        FileWriter writer = new FileWriter("src/balancing.out",false);
        writer.write(Integer.toString(b,10));
        writer.close();

    }
    public static int maxDiff(int[] quadData) {
        int max = 0;
        int min = quadData[0];
        for(int quad: quadData){
            max = Math.max(max,quad);
            min = Math.min(min,quad);
        }
        return max-min;
    }
    public static int[] balance(Point[] points, int x, int y) {
        int[] countArray = new int[4]; // Array to store the count of points in each quarter
        for (Point point : points) {
            countArray[point.getQuadrant(new Point(x,y))]++; // Increment the count for the corresponding quarter
        }
        return countArray;
    }
    public static Point pointDiff(Point[] points, int x, int y) {
        int leftCount = 0;
        int rightCount = 0;
        int aboveCount = 0;
        int belowCount = 0;

        for (Point point : points) {
            if (point.getX() < x) {
                leftCount++;
            } else {
                rightCount++;
            }

            if (point.getY() < y) {
                belowCount++;
            } else {
                aboveCount++;
            }
        }

        int xDiff = rightCount - leftCount;
        int yDiff = aboveCount - belowCount;

        return new Point(xDiff, yDiff);
    }

}