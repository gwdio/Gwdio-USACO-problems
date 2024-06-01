import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
//2:55:13 secs to complete http://www.usaco.org/index.php?page=viewproblem2&cpid=1061
public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int len = reader.nextInt();
        Object[][] cowData = new Object[len][3];
        reader.nextLine();
        String[] tempCord;
        for (int i = 0; i < len; i++) {
            cowData[i][0] = reader.next("[NE]").charAt(0);
            tempCord = reader.nextLine().trim().split(" ");
            cowData[i][1] = new Coordinate(Integer.parseInt(tempCord[0]),Integer.parseInt(tempCord[1]));
            cowData[i][2]= i; //original index
        }
        reader.close();
        Arrays.sort(cowData, Comparator.comparingInt
                (arr->Integer.MAX_VALUE-((((Coordinate)arr[1]).getX())+((Coordinate)arr[1]).getY())));
        int[] results = new int[len];
        for (int i = 0; i < len; i++) {
            switch ((Character)cowData[i][0]){
                case 'E' -> results[i] = checkE(i,cowData);
                case 'N' -> results[i] = checkN(i,cowData);
            }
        }
        results = sortResults(results,cowData);
        for (int result : results) {
            if (result == Integer.MAX_VALUE) {
                System.out.println("Infinity");
            } else {
                System.out.println(result);
            }
        }
//        System.out.println(Arrays.toString(results)); //debugging
//        System.out.println(((Coordinate)cowData[2][1]).getWall());


    }
    public static int checkE(int loc, Object[][] data) {
        int firstEndPoint = Integer.MAX_VALUE;
        Coordinate coordinate = (Coordinate) data[loc][1];
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (Object[] cow : data) {
            if (cow == data[loc]) break; // exits if comparing the same object
            if (cow[0].equals('E')) { // exits if both same direction, handles at same Y
                if (y == ((Coordinate) cow[1]).getY()) {
                    int endPoint = (((Coordinate) cow[1]).getX()) - x;
                    firstEndPoint = updateFirstEndPoint(firstEndPoint, endPoint);
                }
                continue;
            }
            int endPoint = compareCords('E', coordinate, (Coordinate) cow[1]);
            firstEndPoint = updateFirstEndPoint(firstEndPoint, endPoint);
            // finds first intersection
        }
        coordinate.setWall(firstEndPoint);
        return firstEndPoint;
    }

    public static int checkN(int loc, Object[][] data) {
        int firstEndPoint = Integer.MAX_VALUE;
        Coordinate coordinate = (Coordinate) data[loc][1];
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (Object[] cow : data) {
            if (cow == data[loc]) break; // exits if comparing the same object
            if (cow[0].equals('N')) { // exits if both same direction, handles at same X
                if (x == ((Coordinate) cow[1]).getX()) {
                    int endPoint = (((Coordinate) cow[1]).getY()) - y;
                    firstEndPoint = updateFirstEndPoint(firstEndPoint, endPoint);
                }
                continue;
            }
            int endPoint = compareCords('N', coordinate, (Coordinate) cow[1]);
            firstEndPoint = updateFirstEndPoint(firstEndPoint, endPoint);
            // Finds first intersection
        }
        if (firstEndPoint > 0) coordinate.setWall(firstEndPoint);
        return firstEndPoint;
    }

    public static int updateFirstEndPoint(int firstEndPoint, int endPoint) {
        return Math.min(firstEndPoint,endPoint);
    }

    public static int compareCords(char dir, Coordinate cow, Coordinate other){
        int x = cow.getX();
        int y = cow.getY();
        int oX = other.getX();
        int oY = other.getY();
        int distance=Integer.MAX_VALUE;
        switch (dir){
            case 'E':{
                if((y < oY)||(x>=oX)) return distance;
                if(!race('E',x,oY,oX,y, other.getWall())) distance = oX-x;
                break;
            }
            case 'N':{
                if((x<oX)||(y>=oY)) return distance;
                if (!race('N',oX,y,x,oY, other.getWall())) distance = oY-y;
                break;
            }
        }
        return distance;
    }
    public static boolean race(char dir, int x, int y, int cX, int cY, int wall) {
        return switch (dir) {
            case 'E' -> cX - x <= cY - y || cY - y > wall;
            case 'N' -> cY - y <= cX - x || cX - x > wall;
            default -> throw new InputMismatchException("direction not supported");
        };
    }
    public static int[] sortResults(int[] results, Object[][] cowData) {
        int[] sorted = new int[results.length];
        for (int i = 0; i < results.length; i++) {
            sorted[(int) cowData[i][2]]=results[i];
        }
        return sorted;
    }

}

