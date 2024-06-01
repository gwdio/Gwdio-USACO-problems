public class Pair {
    private int a, b, c;
    private Pair swapPair;
    public Pair(){
        this.a=0;
        this.b=0;
    }
    public Pair(int a, int b){
        this.a=a;
        this.b=b;
    }
    public Pair(Pair ab, int c){
        this.swapPair=ab;
        this.c=c;
    }
    public int getA(){
        return this.a;
    }
    public int getB(){
        return this.b;
    }
    public int getC(){
        return this.c;
    }
    public Pair getSwapPair(){
        return this.swapPair;
    }
    public static Pair parseFromString(String input) {
        String[] values = input.split(" ");
        if (values.length != 3) {
            throw new IllegalArgumentException("Invalid input format");
        }
        int a = Integer.parseInt(values[0]);
        int b = Integer.parseInt(values[1]);
        int c = Integer.parseInt(values[2]);
        Pair innerPair = new Pair(a, b);
        return new Pair(innerPair, c);
    }
}
