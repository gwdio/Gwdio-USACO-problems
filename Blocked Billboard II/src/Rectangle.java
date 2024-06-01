public class Rectangle {
    private Coordinate[] corners;
    private int[] dimensions;

    public Rectangle(Coordinate coord1, Coordinate coord2) {
        corners = new Coordinate[4];
        corners[0] = new Coordinate(Math.min(coord1.getX(), coord2.getX()),
                                    Math.max(coord1.getY(), coord2.getY()));
        corners[1] = new Coordinate(Math.max(coord1.getX(), coord2.getX()),
                                    Math.max(coord1.getY(), coord2.getY()));
        corners[2] = new Coordinate(Math.max(coord1.getX(), coord2.getX()),
                                    Math.min(coord1.getY(), coord2.getY()));
        corners[3] = new Coordinate(Math.min(coord1.getX(), coord2.getX()),
                                    Math.min(coord1.getY(), coord2.getY()));

        dimensions = new int[4];
        dimensions[0] = corners[1].getY();
        dimensions[1] = corners[1].getX();
        dimensions[2] = corners[3].getY();
        dimensions[3] = corners[3].getX();
    }

    public Coordinate[] getCorners() {
        return corners;
    }

    public int[] getDimensions() {
        return dimensions;
    }
    public boolean containsCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        // Check if the coordinate is within the x and y range of the rectangle
        return x >= dimensions[3] && x <= dimensions[1] && y >= dimensions[2] && y <= dimensions[0];
    }
    public int area() {
        int width = dimensions[1] - dimensions[3];
        int height = dimensions[0] - dimensions[2];
        return width * height;
    }
    public int findSideInside(Rectangle other) throws InvalidRectangleException {
        for (int i = 0; i < 4; i++) {
            if ((this.dimensions[i]<other.dimensions[Main.wrap(i,2)])&&
                    (this.dimensions[i]>other.dimensions[i])) return i;
        }
        throw new InvalidRectangleException("rectangle not properly vetted to have 1 interior side");
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangle:\n");
        for (int i = 0; i < 4; i++) {
            sb.append("Corner ").append(i).append(": ").append(corners[i]).append("\n");
        }
        sb.append("Top: ").append(dimensions[0]).append("\n");
        sb.append("Right: ").append(dimensions[1]).append("\n");
        sb.append("Bottom: ").append(dimensions[2]).append("\n");
        sb.append("Left: ").append(dimensions[3]);
        return sb.toString();
    }
}
class InvalidRectangleException extends Exception{
    public InvalidRectangleException(String s){
        super(s);
    }
    public InvalidRectangleException(){
        super();
    }
}
