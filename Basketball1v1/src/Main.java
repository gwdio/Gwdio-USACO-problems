import java.lang.Exception;
import java.util.Arrays;
class improperFormatException extends Exception{
    public improperFormatException(String problem){
        super(problem);
    }
}
public class Main {
    static final String gameScore = "B2A2B1A2B2A2A1B1A1A1B2A2B1A2B1A2A1A2B2A1B1";
    public static void main(String[] args) throws improperFormatException {
        int a=0, b=0;
        String[] score = gameScore.split("(?=[AB])");
        //System.out.print(Arrays.toString(score));
        for(String test: score){
            //System.out.println(test);
            switch (test){
                case "A1":
                    a++;
                    break;
                case "A2":
                    a+=2;
                    break;
                case "B1":
                    b++;
                    break;
                case "B2":
                    b+=2;
                    break;
                default:
                    throw new improperFormatException("Data is not formatted properly");
            }
            if (a>=11&&a>=b+2) {System.out.print("A"); break;}
            if (b>=11&&b>=a+2) {System.out.print("B"); break;}
        }
        System.out.print(a+" "+b);
        if (a>b) System.out.print("A"); else System.out.print("B");
    }
}