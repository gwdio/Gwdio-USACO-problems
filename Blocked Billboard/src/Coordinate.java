public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        assert obj instanceof Coordinate;
        Coordinate other = (Coordinate) obj;
        return this.x == other.getX() && this.y == other.getY();
    }

    public int in(Coordinate[] coordinates) {
        System.out.println("\nrunning coordinate checker for object " +this);
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println(this + coordinates[i].toString()); //debugging
            if (this.equals(coordinates[i])) {
                return i;
            }
        }
        return -1;
    }
}