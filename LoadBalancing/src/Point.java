public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getQuadrant(Point center) {
        if (x > center.getX() && y > center.getY()) {
            return 1;
        } else if (x < center.getX() && y > center.getY()) {
            return 2;
        } else if (x < center.getX() && y < center.getY()) {
            return 3;
        } else if (x > center.getX() && y < center.getY()) {
            return 0;
        } else {
            throw new IllegalArgumentException("numbers or code does not work correctly for getQuadrant");
        }
    }
}
