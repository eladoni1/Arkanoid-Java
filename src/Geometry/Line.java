package Geometry;

public class Line {
    private Point start, end;
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public double length() {
        if (start == null || end == null) {
            return 0.0;
        }
        return start.distance(end);
    }
    public Point middle() {
        double newX = (start.getX() + end.getX()) / 2.0;
        double newY = (start.getY() + end.getY()) / 2.0;
        return new Point(newX, newY);
    }

    public Point start() {
        return this.start;
    }
    public Point end() {
        return this.end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public boolean isIntersecting(Line other) {
        Point p;
        if ((p = intersection(other)) == null) {
            return false;
        }
        // check if it is within bounds of the two lines.
        return isInBoundary(p);
    }

    public boolean isInBoundary(Point p) {
        double large_x, little_x, large_y, little_y;
        if (this.start.getX() > this.end.getX()) {
            large_x = this.start.getX();
            little_x = this.end.getX();
        } else {
            large_x = this.end.getX();
            little_x = this.start.getX();
        }
        if (this.start.getY() > this.end.getY()) {
            large_y = this.start.getY();
            little_y = this.end.getY();
        } else {
            large_y = this.end.getY();
            little_y = this.start.getY();
        }
//        if (p.getX() == 4 && p.getY() == 4) {
//            System.out.println(Double.toString(little_x) + " " +  Double.toString(large_x)  + " " +  Double.toString(little_y)  + " " +  Double.toString(large_y));
//            System.out.println("start:" + this.start);
//            System.out.println("end:" + this.end);
//        }
        return large_x >= p.getX() && little_x <= p.getX() && large_y >= p.getY() && little_y <= p.getY();
    }

//    public double getSlope() {
//        if (start == null || end == null) {
//            return 0.0;
//        }
//        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
//    }

    public Point intersection(Line other) {
        // a1x + b1y = c1
        double a1 = other.end().getY() - other.start().getY();
        double b1 = other.start().getX() - other.end().getX();
        double c1 = a1 * other.start().getX() + b1 * other.start().getY();

        // a2x + b2y = c2
        double a2 = this.end.getY() - this.start.getY();
        double b2 = this.start.getX() - this.end.getX();
        double c2 = a2 * this.start.getX() + b2 * this.start.getY();

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // They are parallel. Not intersecting in any way.
//            return new Geometry.Point(Double.MAX_VALUE, Double.MAX_VALUE);
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            x = (double)Math.round(x * 100000d) / 100000d;
            y = (double)Math.round(y * 100000d) / 100000d;
            return new Point(x, y);
        }
    }

    public Point intersectionWith(Line other) {
        return this.intersection(other);
    }

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pArr = rect.intersectionPoints(this);
        if (pArr.isEmpty()) {
            return null;
        }
        double minDistance = Double.MAX_VALUE;
        double temp = 0.0;
        Point closest = null;
        for (Point p : pArr) {
            if ((temp = start.distance(p)) < minDistance) {
                closest = p;
                minDistance = temp;
            }
        }
        return closest;
    }

}
