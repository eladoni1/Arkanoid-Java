package GameObjects;

import ArkanoidClasses.Game;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.*;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite, HitNotifier {
    Rectangle rec;
    Color color = Color.BLACK; // default color.
    int value = 10; // default value.
    private List<HitListener> hitListeners = new ArrayList<>();

    public Block(Rectangle rectangle) {
        rec = rectangle;
    }
    public Block(Point upperLeft, double width, double height) {
        rec = new Rectangle(upperLeft, width, height);
    }
    public Block(double dx, double dy, double width, double height) {
        rec = new Rectangle(dx, dy, width, height);
    }
    public Block(double dx, double dy, double width, double height, Color c, int v) {
        rec = new Rectangle(dx, dy, width, height);
        color = c;
        value = v;
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // same object
        if (collisionPoint.getY() == rec.getUpperLeft().getY() || collisionPoint.getY() == rec.getUpperLeft().getY() + rec.getHeight()) {
            currentVelocity.setyAxis(currentVelocity.getyAxis() * (-1));
        }
        if (collisionPoint.getX() == rec.getUpperLeft().getX() || collisionPoint.getX() == rec.getUpperLeft().getX() + rec.getWidth()) {
            currentVelocity.setxAxis(currentVelocity.getxAxis() * (-1));
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int)rec.getUpperLeft().getY(), (int)rec.getWidth(), (int)rec.getHeight());
        // d.drawRectangle();
    }

    @Override
    public void timePassed() {
        // Change graphical effect? (special event / special blocks with special scores)
        // Todo be creative
    }

    public void addToGame(Game g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
    public void removeFromGame(Game g) {
        if (g != null) {
            g.removeCollidable(this);
            g.removeSprite(this);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.add(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.remove(hl);
        }
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    public int getValue() {
        return value;
    }
}
