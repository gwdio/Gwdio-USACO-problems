public class Range {
    private final int start;
    private final int end;

    public Range(int value1, int value2) {
        // Ensure that start is set to the smaller value
        this.start = Math.min(value1, value2);
        this.end = Math.max(value1, value2);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getRange() {
        return end - start;
    }
    @Override
    public String toString(){
        return "["+start+","+end+"] R: "+(end-start);
    }
}
