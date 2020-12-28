package parts;

public class Coord {

    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCardArea(Coord coord, int x, int y) {
        if (x >= coord.getX() && x <= coord.getX() + SizeKeeper.IMAGE_WIDTH &&
            y >= coord.getY() && y <= coord.getY() + SizeKeeper.IMAGE_HEIGHT) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "logic.Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
