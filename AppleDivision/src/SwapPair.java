public class SwapPair {
    private int first, second;
    private int diff;
    public SwapPair(int first, int second){
        this.first = first;
        this.second = second;
        diff = first-second;
    }
    public SwapPair(){}
    public SwapPair(SwapPair other){
        this.first = other.first;
        this.second = other.second;
        this.diff = first-second;
    }

    public void setFirst(int first) {
        this.first = first;
        diff = first-second;
    }

    public void setSecond(int second) {
        this.second = second;
        diff = first-second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getDiff() {
        return diff*2;
    }

}
