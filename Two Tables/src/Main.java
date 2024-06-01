import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i <len; i++) {
            int w = scanner.nextInt();
            int h = scanner.nextInt();
            scanner.nextLine();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            scanner.nextLine();
            int xt = scanner.nextInt();
            int yt = scanner.nextInt();
            scanner.nextLine();
            int width = x2-x1;
            int height = y2-y1;
            if(width + xt > w && height + yt > h){
                System.out.println(-1);
            } else {
                int xShift = xt-x1;
                int yShift = yt-y1;
                if (width + xt > w) xShift=Integer.MAX_VALUE;
                if(height + yt > h) yShift=Integer.MAX_VALUE;
                System.out.println(Math.max(Math.min(xShift,yShift),0));
            }
        }
        scanner.close();
    }
}