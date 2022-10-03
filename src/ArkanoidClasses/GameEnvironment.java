package ArkanoidClasses;

import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;
import Interfaces.Sprite;

import java.util.List;

public class GameEnvironment {
    List<Collidable> collidables;

    public GameEnvironment() {
        collidables = new java.util.ArrayList<>();
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) { if ( c!=null ) collidables.add(c); }
    public void removeCollidable(Collidable c) { if (c!= null) collidables.remove(c); }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point start = trajectory.start();
        double minDistance = Double.MAX_VALUE, temp;
        Point whereAt = null;
        Collidable whichOne = null;
        for (Collidable c : collidables) {
            List<Point> curr = c.getCollisionRectangle().intersectionPoints(trajectory);
            for (Point p : curr) {
                if ((temp = start.distance(p)) < minDistance) {
                    minDistance = temp;
                    whereAt = p;
                    whichOne = c;
                }
            }
        }
        if (whereAt == null) {
            return null;
        }
        return new CollisionInfo(whereAt, whichOne);
    }

}