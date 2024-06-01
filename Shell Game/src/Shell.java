public class Shell {
    private boolean[] game;
    private int correct;
    public Shell(){
        game = new boolean[]{false, false, false};
        correct = 0;
    }
    public Shell(int n){
        game = new boolean[]{false, false, false};
        game[n-1] = true;
        correct = 0;
    }
    public void swapCheckCount(Pair ab){
        boolean temp;
        temp = this.game[ab.getSwapPair().getA()-1];
        this.game[ab.getSwapPair().getA()-1]=this.game[ab.getSwapPair().getB()-1];
        this.game[ab.getSwapPair().getB()-1]=temp;
        if (this.game[ab.getC() - 1]) this.correct++;
    }
    public int getCorrect(){
        return this.correct;
    }
//    public boolean checkGuess(int c){
//        return (game[c-1]==true);
//    }
}
