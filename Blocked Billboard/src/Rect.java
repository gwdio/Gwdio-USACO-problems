public class Rect {
    private Coordinate corner1;
    private Coordinate corner2;

    public Rect(Coordinate corner1, Coordinate corner2) {
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public Coordinate getCorner1() {
        return corner1;
    }

    public Coordinate getCorner2() {
        return corner2;
    }
    private Coordinate[] getCoordinates() {
        Coordinate[] coordinates = new Coordinate[4];
        coordinates[0] = corner1;
        coordinates[1] = new Coordinate(corner2.getX(), corner1.getY());
        coordinates[2] = corner2;
        coordinates[3] = new Coordinate(corner1.getX(), corner2.getY());
        return coordinates;
    }
    private int findCord(Coordinate c){
        Coordinate[] data = this.getCoordinates();
        int index = c.in(data);
        if(index==-1) throw new IllegalArgumentException("Cord in findCord not present");
        if(data[index].getX() <= data[(index+1)%4].getX() &&
                data[index].getX() <= data[(index+2)%4].getX() &&
                data[index].getX() <= data[(index+3)%4].getX() &&
                data[index].getY() >= data[(index+1)%4].getY() &&
                data[index].getY() >= data[(index+2)%4].getY() &&
                data[index].getY() >= data[(index+3)%4].getY()){
            return 0; //top left
        }
        if(data[index].getX() >= data[(index+1)%4].getX() &&
                data[index].getX() >= data[(index+2)%4].getX() &&
                data[index].getX() >= data[(index+3)%4].getX() &&
                data[index].getY() >= data[(index+1)%4].getY() &&
                data[index].getY() >= data[(index+2)%4].getY() &&
                data[index].getY() >= data[(index+3)%4].getY()){
            return 1; //top right
        }
        if(data[index].getX() >= data[(index+1)%4].getX() &&
                data[index].getX() >= data[(index+2)%4].getX() &&
                data[index].getX() >= data[(index+3)%4].getX() &&
                data[index].getY() <= data[(index+1)%4].getY() &&
                data[index].getY() <= data[(index+2)%4].getY() &&
                data[index].getY() <= data[(index+3)%4].getY()){
            return 2; //bottom right
        }
        if(data[index].getX() <= data[(index+1)%4].getX() &&
                data[index].getX() <= data[(index+2)%4].getX() &&
                data[index].getX() <= data[(index+3)%4].getX() &&
                data[index].getY() <= data[(index+1)%4].getY() &&
                data[index].getY() <= data[(index+2)%4].getY() &&
                data[index].getY() <= data[(index+3)%4].getY()){
            return 3; //bottom left
        }
        throw new IllegalArgumentException("Cord did something stupid");
    }
    private Coordinate where(int x){
        return switch (x){
            case 0 -> new Coordinate(
                    Math.min(this.getCorner1().getX(),this.getCorner2().getX()),
                    Math.max(this.getCorner1().getY(),this.getCorner2().getY()));
            case 1 -> new Coordinate(
                    Math.max(this.getCorner1().getX(),this.getCorner2().getX()),
                    Math.max(this.getCorner1().getY(),this.getCorner2().getY()));
            case 2 -> new Coordinate(
                    Math.max(this.getCorner1().getX(),this.getCorner2().getX()),
                    Math.min(this.getCorner1().getY(),this.getCorner2().getY()));
            case 3 -> new Coordinate(
                    Math.min(this.getCorner1().getX(),this.getCorner2().getX()),
                    Math.min(this.getCorner1().getY(),this.getCorner2().getY()));
            default -> throw new IllegalArgumentException("direction does not exist");
        };
    }


    private boolean has(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        int minX = Math.min(corner1.getX(), corner2.getX());
        int maxX = Math.max(corner1.getX(), corner2.getX());
        int minY = Math.min(corner1.getY(), corner2.getY());
        int maxY = Math.max(corner1.getY(), corner2.getY());

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    public int area() {
        int width = Math.abs(corner2.getX() - corner1.getX());
        int height = Math.abs(corner2.getY() - corner1.getY());
        return width * height;
    }
    private int area(Coordinate used, Coordinate a, Coordinate b){
        int height;
        int width;
        if(a.getX()==b.getX()){
            width = Math.abs(used.getX()-a.getX());
            height = Math.abs(a.getY()-b.getY());
        } else if(a.getY()==b.getY()){
            width = Math.abs(used.getY()-a.getY());
            height = Math.abs(a.getX()-b.getX());
        } else throw new IllegalArgumentException("wrong Coordinate used");
        return width*height;
    }
    public int calcOverlap(Rect truck){
        int truckCordsIn = 0;
        boolean[]truckIn = new boolean[4];
        Coordinate[] truckCords = truck.getCoordinates();
        Coordinate[] boardCords = this.getCoordinates();
        for (int i = 0; i < truckCords.length; i++) {
            if (this.has(truckCords[i])) {
                truckCordsIn++;
                truckIn[i]=true;
            }

        }
        System.out.println("\nPrinting truckCords"); //debugging
        for(Coordinate c: truckCords){System.out.print(c);} //debugging
        int area = 0;
        switch (truckCordsIn) {
            case 0 -> {
                System.out.println("\nCase 0 triggered"); //debugging
                for (int i = 0; i < 4; i++) {
                    if (truck.has(boardCords[i])) {
                        return truck.calcOverlap(this);
                    }
                }
            }
            case 1 -> {
                System.out.println("\nCase 1 triggered"); //debugging
                Rect overlap = new Rect(truckCords[findIndex(truckIn)],
                        where((truck.findCord(truckCords[findIndex(truckIn)]) + 2) % 4));
                area = overlap.area();
            }
            case 2 -> {
                System.out.println("\nCase 2 triggered"); //debugging
                if (truck.findCord(truckCords[findIndex(truckIn)]) == 0) {
                    area = area(where(2), truckCords[findIndexes(truckIn)[0]], truckCords[findIndexes(truckIn)[1]]);
                } else {
                    area = area(where(0), truckCords[findIndexes(truckIn)[0]], truckCords[findIndexes(truckIn)[1]]);
                }
            }
            case 4 -> {
                System.out.println("\nCase 4 triggered"); //debugging
                area = truck.area();
            }
            default -> throw new IllegalArgumentException("case 3 or other cannot exist");
        }
        return area;
    }
    private static int findIndex(boolean[] array) {
        for (int i = 0; i < 4; i++) {
            if (array[i]) {
                return i;
            }
        }
        return -1; // No true value found
    }
    private static int[] findIndexes(boolean[] arr) {
        int[] indexes = new int[2];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i]) {
                indexes[index++] = i;
            }
        }
        return indexes;
    }

    @Override
    public String toString() {
        return "Rectangle: Corner1 " + corner1 + ", Corner2 " + corner2;
    }
}