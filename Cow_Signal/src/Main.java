
public class Main {
    public static void main(String[] args) {
        InputInterpret input = new InputInterpret();
        Grower grower = new Grower(input);
        grower.grow();
    }
}