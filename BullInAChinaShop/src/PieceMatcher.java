public final class PieceMatcher {
    public static Piece completePiece;
    public static int N;
    public static int K;

    public static void setCompletePiece(Piece cPiece){
        completePiece=cPiece;
    }
    public static void setN(int n){
        N=n;
    }

    public static void setK(int k) {
        K = k;
    }

    public static int[] testAll(Piece[] pieces){
        for (int i = 0; i < K - 1; i++) {
            for (int j = i + 1; j < K; j++) {
                // Get the ith and jth pieces
                System.out.println("testing piece combination "+i+"&"+j);
                Piece piece1 = pieces[i];
                Piece piece2 = pieces[j];
                if(piece1.getAmtTrue() + piece2.getAmtTrue()!= completePiece.getAmtTrue()) continue;
                //sum of parts must equal whole
                if (match(piece1,piece2)){
                    return new int[] {(i+1),(j+1)};
                }
            }
        }
        throw new IllegalArgumentException("No valid configurations found");
    }

    private static boolean match(Piece piece1, Piece piece2) {
        Range piece1Width = piece1.getWidth();
        Range piece1Height = piece1.getHeight();
        Range piece2Width = piece2.getWidth();
        Range piece2Height = piece2.getHeight();

        for (int shiftX1 = -piece1Width.getStart(); shiftX1 < N - piece1Width.getEnd(); shiftX1++) {
            for (int shiftY1 = -piece1Height.getStart(); shiftY1 < N - piece1Height.getEnd(); shiftY1++) {
                for (int shiftX2 = -piece2Width.getStart(); shiftX2 < N - piece2Width.getEnd(); shiftX2++) {
                    for (int shiftY2 = -piece2Height.getStart(); shiftY2 < N - piece2Height.getEnd(); shiftY2++) {
                        System.out.println("testing the following: "+ formatShifts(shiftX1,shiftY1,shiftX2,shiftY2));
                        if (isMatch(piece1, shiftX1, shiftY1, piece2, shiftX2, shiftY2)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean isMatch(Piece piece1, int shiftX1, int shiftY1,
                                   Piece piece2, int shiftX2, int shiftY2){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //iterates through all elements of completePiece
                // Check if the indices are within the valid range
                boolean indicesValid =  (i + shiftX1 >= 0) &&
                                        (i + shiftX1 < N) &&  //x1 in bounds
                                        (j + shiftY1 >= 0) &&
                                        (j + shiftY1 < N) && //y1 in bounds
                                        (i + shiftX2 >= 0) &&
                                        (i + shiftX2 < N) && //x2 in bounds
                                        (j + shiftY2 >= 0) &&
                                        (j + shiftY2 < N); //y2 in bounds

                if (!indicesValid) {
                    // Indices are out of bounds, continue with the next iteration
                    continue;
                }

                // Compare the elements and return false if they don't match
                if (completePiece.getPiece()[i][j]
                        != (piece1.getPiece()[i + shiftX1][j + shiftY1]
                        ^ piece2.getPiece()[i + shiftX2][j + shiftY2])) {
                    return false;
                }
            }
        }
        return true;
    }
    private static String formatShifts(int sX1, int sY1, int sX2, int sY2){
        return sX1 + ", " + sY1 + ", " + sX2 + ", " + sY2;
    }
}
