package GameObjects;

import Geometry.Point;

// Collidables.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    double xAxis;
    double yAxis;
    // Constructor
    public Velocity(double dx, double dy) {
        xAxis = dx;
        yAxis = dy;
    }

    // Copy constructor
    public Velocity(Velocity v) {
        xAxis = v.xAxis;
        yAxis = v.yAxis;
    }

    public double getxAxis() {
        return xAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle - 90.0;
        if (angle <= 0) {
            angle += 360.0;
        }
        double radians = Math.toRadians(angle);

        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        Velocity v = new Velocity(dx, dy);
        return v;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + xAxis, p.getY() + yAxis);
    }
}