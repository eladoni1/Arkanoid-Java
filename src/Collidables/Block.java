package Collidables;

import ArkanoidClasses.Game;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.*;
import biuoop.DrawSurface;

public class Block implements Collidable, Sprite {
    Rectangle rec;
    public Block(Rectangle rectangle) {
        rec = rectangle;
    }
    public Block(Point upperLeft, double width, double height) {
        rec = new Rectangle(upperLeft, width, height);
    }
    public Block(double dx, double dy, double width, double height) {
        rec = new Rectangle(dx, dy, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // same object
        if (collisionPoint.getY() == rec.getUpperLeft().getY() || collisionPoint.getY() == rec.getUpperLeft().getY() + rec.getHeight()) {
            currentVelocity.setyAxis(currentVelocity.getyAxis() * (-1));
        }
        if (collisionPoint.getX() == rec.getUpperLeft().getX() || collisionPoint.getX() == rec.getUpperLeft().getX() + rec.getWidth()) {
            currentVelocity.setxAxis(currentVelocity.getxAxis() * (-1));
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int)rec.getUpperLeft().getY(), (int)rec.getWidth(), (int)rec.getHeight());
    }

    @Override
    public void timePassed() {
        // Change graphical effect? (special event / special blocks with special scores)
        // Todo be creative
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
