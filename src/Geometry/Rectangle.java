package Geometry;

import java.util.ArrayList;

public class Rectangle {
    private Point upperLeft;
    private double w;
    private double h;
    // todo add color in here or in block.

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        w = width;
        h = height;
    }

    public Rectangle(double dx, double dy, double width, double height) {
        upperLeft = new Point(dx, dy);
        w = width;
        h = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pArr = new ArrayList<>();
        double oldX = upperLeft.getX(), oldY = upperLeft.getY(), newX = oldX + w, newY = oldY + h;
        Point p1 = upperLeft, p2 = new Point(oldX, newY), p3 = new Point(newX, oldY), p4 = new Point(newX, newY);
        // Geometry.Line l1 = new Geometry.Line(p1, p2), l2 = new Geometry.Line(p1, p3), l3 = new Geometry.Line(p2, p4), l4 = new Geometry.Line(p3, p4);
        Point possible;
        Line[] lines = new Line[] {new Line(p1, p2), new Line(p1, p3), new Line(p2, p4), new Line(p3, p4)};
        for (int i = 0; i < 4; i++) {
            possible = lines[i].intersection(line);
            if (possible != null && lines[i].isInBoundary(possible) && line.isInBoundary(possible) && !pArr.contains(possible)) {
                pArr.add(possible);
            }
        }
        return pArr;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return w;
    }
    public double getHeight() {
        return h;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }

}