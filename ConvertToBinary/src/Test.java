import java.util.InputMismatchException;
public class Test {
    public static void main(String[] args) {
        final char START = ' ';
        final char END = 'p';
        if(END<START) throw new InputMismatchException("You suck");
        for (char c = START; c <= END; c++) {
            System.out.print(c);
        }
    }
}