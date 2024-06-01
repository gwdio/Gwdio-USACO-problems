import java.util.Arrays;

public class Leaderboard {
    private int[] leaderboard;
    public Leaderboard(int[] in){
        leaderboard = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            leaderboard[in[i]-1]=i;
        }
    }
    public Leaderboard(){
        leaderboard=null;
    }

    public int[] getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(int[] leaderboard) {
        this.leaderboard = Arrays.copyOf(leaderboard,leaderboard.length);
    }

    public boolean equals(int pos, Leaderboard other) {
        for (int i = 0; i < this.leaderboard.length; i++) {
            if(i==pos) continue;
            if((this.leaderboard[i]>this.leaderboard[pos]&&
                    other.leaderboard[i]>other.leaderboard[pos])||
                    (this.leaderboard[i]<this.leaderboard[pos]&&
                    other.leaderboard[i]<other.leaderboard[pos])){
                return true;
            }
        }
        return false;
    }
}
