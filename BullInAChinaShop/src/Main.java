import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("src/bcs.in");
        Scanner scanner = new Scanner(input);
        final int N = scanner.nextInt();
        final int K = scanner.nextInt();
        scanner.nextLine();
        Piece.setN(N);
        PieceMatcher.setN(N);
        PieceMatcher.setK(K);
        boolean[][] temp = new boolean[N][];
        for (int i = 0; i < N; i++) {
            temp[i] = Utils.hash(scanner.nextLine().trim().toCharArray());
        }
        Piece complete = new Piece(temp);
        PieceMatcher.setCompletePiece(complete);
        Piece[] pieces = new Piece[K];
        for (int i = 0; i < K; i++) {
            temp = new boolean[N][];
            for (int j = 0; j < N; j++) {
                temp[j]= Utils.hash(scanner.nextLine().trim().toCharArray());
            }
            pieces[i] = new Piece(temp);
        }
        //prints out the stuff in the input
        System.out.println("Complete");
        complete.printPiece();
        for (int i = 0; i < K; i++) {
            System.out.println("piece "+(i+1));
            pieces[i].printPiece();
        }
        int[] results = PieceMatcher.testAll(pieces);
        assert results.length == 2;
        System.out.println(Arrays.toString(results));
        try (FileWriter writer = new FileWriter("src/bcs.out", false)) {
            writer.write(Integer.toString(results[0], 10) + " " + Integer.toString(results[1], 10));
        }

    }
}