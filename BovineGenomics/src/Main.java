import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException {
        //time program execution
        Instant start = Instant.now();

        int sets = 0; //final count
        boolean setIsFalse = true;
        //taking inputs
        File in = new File("src/cownomics.in");
        Scanner scanner = new Scanner(in);
        final int N = scanner.nextInt();
        final int M = scanner.nextInt();
        scanner.nextLine();
        char[][] spots = new char[N][];
        char[][] notSpots = new char[N][];
        for (int i = 0; i < 3; i++) {
            spots[i]=scanner.nextLine().trim().toCharArray();
            assert spots[i].length == M; //debugging
        }
        for (int i = 0; i < 3; i++) {
            notSpots[i]=scanner.nextLine().trim().toCharArray();
            assert notSpots[i].length == M; //debugging
        }
        char[] temp = new char[3];

        //loops through all M choose 3 possibilities
        for (int m0 = 0; m0 < M-2; m0++) {
            for (int m1 = m0+1; m1 < M-1; m1++) {
                if(m0==m1) continue;
                for (int m2 = m1+1; m2 < M; m2++) {
                    //loops through all inputs in Spots
                    if(m2==m1||m2==m0) continue;
                    System.out.print("\n"+m0+m1+m2);
                    //cow genome
                    boolean[] genome = new boolean[64];
                    for (int i = 0; i < N; i++) {
                        temp[0]=spots[i][m0];
                        temp[1]=spots[i][m1];
                        temp[2]=spots[i][m2];
                        genome[constructByte(temp)] = true;
//                        System.out.print(temp);

                    }
                    for (int i = 0; i < N; i++) {
                        temp[0]=notSpots[i][m0];
                        temp[1]=notSpots[i][m1];
                        temp[2]=notSpots[i][m2];
//                        System.out.print("\n"+ Arrays.toString(temp));
                        if(genome[constructByte(temp)]) {
                            setIsFalse=false;
//                            System.out.println("exit at "+i);
                            break;
                        }
                    }
                    if(setIsFalse){
                        sets++;
                        System.out.print(": Count  = "+sets);
                    } else setIsFalse=true;
                }
            }
        }
        System.out.println("\n\n"+sets);
        FileWriter writer = new FileWriter("src/cownomics.out",false);
        writer.write(Integer.toString(sets,10));
        writer.close();

        // Stop the timer
        Instant end = Instant.now();
        // Calculate the elapsed time
        Duration duration = Duration.between(start, end);
        // Output the elapsed time
        System.out.println("Elapsed time: " + duration.toMillis() + " Milliseconds");
    }
    public static byte constructByte(char[] array) {
        assert array.length == 3; //debugging
        byte result = 0;
        // First value
        result |= switch (array[0]) {
            case 'A' -> 0;
            case 'C' -> 0b01;
            case 'T' -> 0b10;
            case 'G' -> 0b11;
            default -> throw new IllegalArgumentException("Invalid character: " + array[0]);
        };
        // Second value
        result |= switch (array[1]) {
            case 'A' -> 0;
            case 'C' -> 0b01 << 2;
            case 'T' -> 0b10 << 2;
            case 'G' -> 0b11 << 2;
            default -> throw new IllegalArgumentException("Invalid character: " + array[1]);
        };
        // Third value
        result |= switch (array[2]) {
            case 'A' -> 0;
            case 'C' -> 0b01 << 4;
            case 'T' -> 0b10 << 4;
            case 'G' -> 0b11 << 4;
            default -> throw new IllegalArgumentException("Invalid character: " + array[2]);
        };
        return result;
    }

//    public static byte constructByte(char[] array) {
//        assert array.length == 3; // Debugging
//
//        byte result = 0;
//
//        // Count the occurrences of each character
//        int countA = 0;
//        int countC = 0;
//        int countG = 0;
//        int countT = 0;
//
//        for (char ch : array) {
//            switch (ch) {
//                case 'A' -> countA++;
//                case 'C' -> countC++;
//                case 'G' -> countG++;
//                case 'T' -> countT++;
//                default -> throw new IllegalArgumentException("Invalid character: " + ch);
//            }
//        }
//
//        // Assign the counts to the corresponding bits
//        result |= countA;
//        result |= countC << 2;
//        result |= countG << 4;
//        result |= countT << 6;
//
//        return result;
//    }

}