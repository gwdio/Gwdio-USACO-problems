import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        scanner.nextLine();
        int[][] points = new int[N][2];
        for (int i = 0; i < N; i++) {
            points[i][0] = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            points[i][1] = scanner.nextInt();
        }
        scanner.close();
        int maxDistance=0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j==i) continue;
                maxDistance = Math.max(maxDistance,dSquared(points[i][0],points[i][1],points[j][0],points[j][1]));
            }
        }
        System.out.println(maxDistance);
    }
    public static int dSquared(int x1, int y1, int x2, int y2){
        return (int) (Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }
}