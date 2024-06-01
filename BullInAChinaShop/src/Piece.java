import java.util.Arrays;

public class Piece {
    static int N;
    private boolean[][] piece;
    private Range width;
    private Range height;
    private final int amtTrue;
    public Piece(boolean[][] piece){
        this.piece=piece;
        this.height=findHeight();
        this.width=findWidth();
        amtTrue=getAmtTrue();
    }

    public int getAmtTrue() {
        return amtTrue;
    }

    public Range getWidth(){
        return width;
    }

    public Range getHeight() {
        return height;
    }

    public boolean[][] getPiece() {
        return piece;
    }

    public static void setN(int n){
        N=n;
    }
    private Range findWidth() {
        int start = 0;
        for (int j = 0; j < N; j++) {
            if (fWHelper(j)) {
                start = j;
                break;
            }
        }
        int end = 0;
        for (int j = N - 1; j >= start; j--) {
            if (fWHelper(j)) {
                end = j;
                break;
            }
        }
        return new Range(start, end);
    }
    private boolean fWHelper(int index) {
        for (int i = 0; i < N; i++) {
            if (piece[i][index]) {
                return true;
            }
        }
        return false;
    }
    private Range findHeight(){
        int start = 0;
        for (int i = 0; i < N; i++) {
            if (fHHelper(i)){
                start=i;
                break;
            }
        }
        int end = 0;
        for (int i = N-1; i >= start ; i--) {
            if (fHHelper(i)){
                end=i;
                break;
            }
        }
        if (end==0) System.out.println("piece is empty");
        return new Range(start,end);
    }
    private boolean fHHelper(int index){
        for (boolean b: piece[index]) {
            if (b) return true;
        }
        return false;
    }
    private int findTrue(){
        int count = 0;
        for(boolean[] bools: piece){
            for(boolean b: bools){
                if (b) count++;
            }
        }
        return count;
    }
    public void printPiece(){
        for (boolean[] booleans : this.piece) {
            System.out.print("[");
            for (boolean b : booleans) {
                System.out.print((b ? "#" : "."));
            }
            System.out.println("]");
        }
        System.out.println("x range: "+ width.toString()+" y range: "+ height.toString());
    }
}
