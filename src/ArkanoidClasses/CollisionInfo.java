package ArkanoidClasses;

import Geometry.Point;
import Interfaces.Collidable;

public class CollisionInfo {
    Point where;
    Collidable c;
    public CollisionInfo(Point whereAt, Collidable whichOne) {
        where = whereAt;
        c = whichOne;
    }
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return where;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return c;
    }
}