import java.util.Arrays;
public class MorePractice {
    public static void main(String[] args) {
        int[][] arraySquared= new int[5][10];
        for (int i = 0; i <arraySquared.length; i++) {
            for (int j = 0; j < arraySquared[i].length; j++) {
                arraySquared[i][j]=(int)(Math.random()*100);
            }
        }
        //System.out.println(Arrays.deepToString(arraySquared));
        System.out.println(stupidMethod(12345));
    }
    private static double stupidMethod(int a){
        return a;
    }
}
