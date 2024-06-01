import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    static boolean[] cows = new boolean[26];
    static LinkedList<Character> sim = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        File in = new File("src/circlecross.in");
        Scanner scanner = new Scanner(in);
        char[] inputs = scanner.nextLine().trim().toCharArray();
        scanner.close();
        int cowCross = 0;

        long startTime = System.currentTimeMillis(); // Record start time

        for (char current : inputs) {
            if (!cows[toInt(current)]) {
                flipValue(current);
                sim.add(0, current);
            } else {
                cowCross += extract(current);
                flipValue(current);
            }
        }

        long endTime = System.currentTimeMillis(); // Record end time
        long executionTime = endTime - startTime; // Calculate execution time

        FileWriter writer = new FileWriter("src/circlecross.out");
        writer.write(Integer.toString(cowCross, 10));
        writer.close();

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }

    public static void flipValue(char cow) {
        int index = toInt(cow);
        cows[index] = !cows[index];
    }

    public static int toInt(char c) {
        return Integer.parseInt(String.valueOf(c), 36) - 10;
    }

    public static int extract(char c) {
        int jumps = 0;
        Iterator<Character> iterator = sim.iterator();

        while (iterator.hasNext()) {
            Character cow = iterator.next();
            if (cow.equals(c)) {
                iterator.remove();
                break;
            }
            jumps++;
        }

        return jumps;
    }
}
