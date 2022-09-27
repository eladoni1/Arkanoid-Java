package Geometry;

public class Circle {
    private Point center;
    private int r;

    public Circle(double dx, double dy, int r) {
        center = new Point(dx, dy);
        this.r = r;
    }
    public Circle(Point p, int r) {
        center = p;
        this.r = r;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int r) {
        this.r = r;
    }

    public int getRadius() {
        return r;
    }

    public Point getCenter() {
        return center;
    }
}
