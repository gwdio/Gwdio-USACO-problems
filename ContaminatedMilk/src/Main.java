import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InputFormatException {
        File input = new File("src/badmilk.in");
        Scanner scanner = new Scanner(input);
        final int N, M, D, S;
        N = scanner.nextInt();
        M = scanner.nextInt();
        D = scanner.nextInt();
        S = scanner.nextInt();
        int[][] consumption = new int[D][3];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < 3; j++) {
                consumption[i][j] = (j!=2) ? scanner.nextInt()-1: scanner.nextInt();
            }
        }
        ArrayExt.sortCol(consumption,2); //sorts array by time
        int[] sick = new int[N];
        for (int i = 0; i < S; i++) {
            sick[scanner.nextInt()-1] = scanner.nextInt();
        }
        scanner.close();
        int[][] sickProfiles = new int[N][M];
        for(int[] drink: consumption){
            int person = drink[0];
            int milk = drink[1];
            int time = drink[2];
            if(sick[person]!=0){ //if the person who drank the drink is explicitly sick
                if (sickProfiles[person][milk]==0) //first encounter with the milk
                    sickProfiles[person][milk]= (sick[person]> time ? time : -1);
                //if the sick person drank the milk after the sick time it can't be that milk
            }
        }
        int[] candidates = ArrayExt.findSharedMilks(sickProfiles,sick);
        System.out.println(Arrays.toString(candidates));
        int doses = ArrayExt.findMaxCount(candidates,consumption,N);
        System.out.println(doses);
        FileWriter writer = new FileWriter("src/badmilk.out",false);
        writer.write(Integer.toString(doses,10));
        writer.close();
    }
}