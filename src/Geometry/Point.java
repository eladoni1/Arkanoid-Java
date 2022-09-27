package Geometry;

public class Point {
    private double x,y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Calculating the distance between 2 points.
    public double distance(Point other) {
        double temp1 = this.x - other.getX();
        temp1 *= temp1;
        double temp2 = this.y - other.getY();
        temp2 *= temp2;
        return Math.sqrt(temp1 + temp2);
    }
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double newX) { // Only made for the Collidables.Paddle class. So we wont create alot of new Points.
        this.x = newX;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    @Override
    public String toString() {
        return "Geometry.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}