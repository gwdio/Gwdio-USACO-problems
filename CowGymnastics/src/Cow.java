public class Cow {
    private boolean consistent;
    final private int COW_NUM;
    private Leaderboard placings;
    public Cow(int cowNum, Leaderboard config){
        consistent = true;
        COW_NUM = cowNum;
        placings = new Leaderboard();
        placings.setLeaderboard(config.getLeaderboard());
    }
    public void testCow(Leaderboard update){
        if(placings.equals(COW_NUM,update)){
            placings.setLeaderboard(update.getLeaderboard());
        } else {
            consistent=false;
        }
    }
    public boolean isConsistent() {
        return consistent;
    }
}
