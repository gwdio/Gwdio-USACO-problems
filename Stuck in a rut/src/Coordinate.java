public class Coordinate {
    private int x, y;
    private int wall;
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
        wall=Integer.MAX_VALUE;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setWall(int distance){
        wall=distance;
    }

    public int getWall() {
        return wall;
    }
}