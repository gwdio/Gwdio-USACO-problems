import java.util.Arrays;

public class KaprekarArrays {

    static class InputError extends Exception{
        //allows special exceptions user defines
        public InputError(String sus){
            super(sus);
        }
    }
    static void wrongInput(int b) throws KaprekarArrays.InputError {
        //ensures input is legal
        if (!((Math.log10(b) < 4) && (b > 0))) {
            throw new InputError("Big/Small");
        }
        int[] c=toIntArr4(b);
        if (!enough(c)){
            throw new InputError("Triplet");
        }
    }
    public static int[] toIntArr4(int four){
        //sends integer into int array
        int[] ret = new int[4];
        Arrays.fill(ret, 0);
        for (int i = 0; i < 3; i++) {
            ret[i]= (int) (four%Math.pow(10,(3-i)));
        }
        return ret;
    }
    public static int toInt4(int[] ret){
        int four=0;
        for(int a =0; a< ret.length; a++){
            four+=ret[a]*Math.pow(10,(4-a));
        }
        return four;
    }
    public static boolean enough(int[] a){
        //ensures there is enough diversity in the input
        int works=0;
        Arrays.sort(a);
        for (int i = 0; i < 2; i++) {
            if (a[i] != a[i + 1]) works++;
        }
        return works>=2;
    }
    public static int[] reverse(int[] a){
        //reverses order of input array
        int[] c = new int[a.length];
        for(int b = 0; b<a.length; b++){
            c[a.length-b]=a[b];
        }
        return c;
    }
}

