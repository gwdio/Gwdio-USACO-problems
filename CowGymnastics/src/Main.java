import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File in = new File("src/gymnastics.in");
        Scanner scanner = new Scanner(in);
        final int K = scanner.nextInt();
        final int N = scanner.nextInt();
        scanner.nextLine();
        //setup data
        int[] data = new int[N];
        String[] temp = scanner.nextLine().trim().split(" ");
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(temp[i]);
        }
        Leaderboard leaderboard = new Leaderboard(data);
        Cow[] gymnasts = new Cow[N];
        for (int i = 0; i < N; i++) { //populates cow array
            gymnasts[i] = new Cow(i, leaderboard);
        }
        for (int i = 1; i < K; i++) {
            temp = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < N; j++) {
                data[j] = Integer.parseInt(temp[j]);
            }
            Leaderboard update = new Leaderboard(data);
            for(Cow cow: gymnasts){
                if(cow.isConsistent()){
                    cow.testCow(update);
                }
            }
        }
        scanner.close();
        int consistentCount=0;
        for(Cow cow: gymnasts){
            if(cow.isConsistent()){
                consistentCount++;
            }
        }
        System.out.println(consistentCount);
        FileWriter writer = new FileWriter("src/gymnastics.out",false);
        writer.write(Integer.toString(consistentCount,10));
        writer.close();
    }
}