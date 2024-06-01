import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void isTrue(boolean a, boolean b) {
        System.out.print(a == b);
    }

    public static void isTrue(Boolean a, Boolean b) {
        System.out.print(a == b);
    }

    //    public static void isTrue(boolean a, Boolean b){
//        System.out.print(a==b);}
//    public static void main(String[] args) throws IOException {
////        int num = 49999;
////        if(IsPrime.isPrime(num)){
////            System.out.println(num + " is Prime");
////        }
////        else{System.out.println(num + " is not Prime");}
//        boolean x = false;
//        boolean y = false;
//        Boolean z = new Boolean(x);
//        Boolean w = new Boolean(y);
//        Boolean q = w;
//        isTrue(x,y);
//        System.out.println(" should be true");
//        isTrue(w,z);
//        System.out.println(" should be false");
//        isTrue(w,q);
//        System.out.println(" should be true");
//        isTrue(y,z);
//        System.out.println(" should be true");
//
//        final int GOD_ARRAY_LENGTH = 14;
//        ArrayList<Integer> godArray = new ArrayList<Integer>();
//        ArrayList jimmy = new ArrayList(); //??? why the hell dat woman type this
//        new ArrayList<Double>();
//        for(int i=0; i<GOD_ARRAY_LENGTH; i++){
//            godArray.add((int) (Math.random()*100));
//        }
//        godArray.remove(5);
//        godArray.add(5,123);
//        godArray.set(6,124);
    //}
    public static void main(String[] args) {
        ArrayList<Boolean> questions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            questions.add(true);
            myMethod1(questions, i);
        }
        System.out.println(questions);

    }
    public static void myMethod1 (ArrayList arr, int index) {
        switch (index){
            case 0:
                arr.set(index, "Hello");
                System.out.println(index+" Hello");
                break;
            case 1:
                arr.set(index, 123432);
                System.out.println(index+" 123432");
                break;
            case 2:
                arr.set(index, 'C');
                System.out.println(index+" C");
                break;
        }
    }
}
