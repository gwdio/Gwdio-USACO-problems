import java.util.Arrays;
public class Leaderboard {
    private int[] milk;
    private boolean[] top;
    public Leaderboard(/* int a */){
        milk =new int[3];
        Arrays.fill(milk,7);
        top = new boolean[3];
        Arrays.fill(top,true);
    }
//    public Leaderboard(){
//        milk =new int[3];
//        top = new boolean[3];
//    }
    public void updateMilk(String[] data) throws IllegalAccessException {
        switch (data[1]){
            case "Mildred" -> {
                this.milk[0]+=Integer.parseInt(data[2]);
                break;
            }
            case "Elsie" -> {
                this.milk[1]+=Integer.parseInt(data[2]);
                break;
            }
            case "Bessie" -> {
                this.milk[2]+=Integer.parseInt(data[2]);
                break;
            }
            default -> throw new IllegalAccessException("Data mismatch, cow does not exist");
        }
    }
    public void updateTop(){
        int max = 0;
        for(int element: this.milk){
            max=Math.max(max, element);
        }
        for (int i = 0; i < 3; i++) {
            top[i] = milk[i]==max;
        }
    }
    public boolean compareToPast(Leaderboard past){
        return !Arrays.equals(this.getTop(), past.getTop()); //true if changes need to be made
    }
    public boolean[] getTop(){
        return this.top;
    }
    public int[] getMilk() {
        return this.milk;
    }
    public void pastAndFuture(Leaderboard future) {
        this.milk = Arrays.copyOf(future.getMilk(), 3);
        this.top = Arrays.copyOf(future.getTop(), 3);
    }
}