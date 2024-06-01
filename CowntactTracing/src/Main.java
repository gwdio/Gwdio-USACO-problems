import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("src/tracing.in");
        Scanner scanner = new Scanner(input);
        final int N = scanner.nextInt();
        final int T = scanner.nextInt();
        scanner.nextLine();
        char[] cowData = scanner.nextLine().trim().toCharArray();
        boolean[] cows = new boolean[N];
        for (int i = 0; i < N; i++) {
            cows[i]=CowArrays.charToBool(cowData[i]);
        }
        int[][] meetings = new int[T][3];
        // Read input values
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < 3; j++) {
                meetings[i][j] = scanner.nextInt() - (j != 0 ? 1 : 0);
            }
        }
        scanner.close();
        CowArrays.sortArr(meetings);
        int x = 0; //amt triggering cows
        int[] cowY = new int[N]; //min Z
        int[] cowZ = new int[N]; // max Z
        int[] currentlySick = new int[N];
        Arrays.fill(cowY, Integer.MAX_VALUE);
        Arrays.fill(cowZ, Integer.MIN_VALUE);

        for (int i = 0; i < N; i++) { //iterate through all cows
            if (!cows[i]) continue; //if root cow not infected
            currentlySick[i] = 1; // Set the root cow to 1 (infected)

            for (int j = 0; j < T; j++) { //iterates through all meetings chronologically
                int sickCow = CowArrays.cowIn(currentlySick, meetings, j);
                if (sickCow == -1) continue; //if all infected cows not present in meeting
                int otherCow = CowArrays.otherVal(meetings[j], sickCow); //find the other cow present in the meeting
                if (!cows[otherCow]) { //if other cow in meeting ends up NOT SICK
                    //sets cow's MAX transmissivity to the shortest path to arrive to it
                    if (currentlySick[otherCow] == 0) currentlySick[otherCow] = Integer.MIN_VALUE;
                    currentlySick[otherCow] = Math.max(currentlySick[otherCow], -(currentlySick[sickCow]));
                    continue;
                }
                //set cow's transmissivity to lower, safer value
                if (currentlySick[otherCow] == 0 || currentlySick[otherCow] > currentlySick[sickCow] + 1) {
                    currentlySick[otherCow] = currentlySick[sickCow] + 1;
                }
            }
            if (CowArrays.maxK(currentlySick) < CowArrays.minK(currentlySick)) {
                Arrays.fill(currentlySick,0); //reset environment
            } else {
                //record data
                cowY[i] = CowArrays.minK(currentlySick);
                cowZ[i] = CowArrays.maxK(currentlySick);
                x++;
                Arrays.fill(currentlySick,0); //reset environment
            }
        }
        FileWriter writer = new FileWriter("src/tracing.out", false);
        writer.write(Integer.toString(x,10) + " " + CowArrays.findMin(cowY) + " "+ CowArrays.findMax(cowZ));
        writer.close();
    }
}