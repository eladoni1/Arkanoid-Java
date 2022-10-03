package Interfaces;

import GameObjects.Ball;
import GameObjects.Velocity;
import Geometry.Point;
import Geometry.Rectangle;

public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}