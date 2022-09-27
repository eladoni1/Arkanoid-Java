import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;

public class Main {
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(new Point(3, 3), 5, 5);
        Line line = new Line(4, 0, 8, 6);
        java.util.List<Point> pointList = rec.intersectionPoints(line);
        for (Point p : pointList) {
            System.out.println(p);
        }
    }
}
