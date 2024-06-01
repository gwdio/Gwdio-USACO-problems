import java.util.Arrays;

public class EvenMorePractice {
    public static void main(String[] args) {
        int[] a = new int[8];
        for (int j = 0; j < a.length; j++) {
            a[j] = (int) (Math.random() * 100);
        }
        System.out.print(Arrays.toString(a));
    }
}
