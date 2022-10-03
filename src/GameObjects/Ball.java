package GameObjects;


import ArkanoidClasses.*;
import Geometry.*;
import Geometry.Point;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.*;

public class Ball implements Sprite {
    // fields
    Circle c; // Todo add functionality with radius..
    Color color;
    int winHeight;
    int winWidth;
    Velocity velocity;
    GameEnvironment ge; // Todo check if needed, or set with 'set' function.

    // constructors
    public Ball(Point center, int r, Color color, int width, int height) {
        this.c = new Circle(center, r);
        this.color = color;
        this.velocity = null;
        this.winHeight = height;
        this.winWidth = width;
        this.ge = new GameEnvironment();
    }

    public Ball(double x, double y, int r, Color color, int width, int height) {
        this.c = new Circle(new Point(x, y), r);
        this.color = color;
        this.velocity = null;
        this.winHeight = height;
        this.winWidth = width;
        this.ge = new GameEnvironment();
    }

    public Ball(double x, double y, int r, Color color, Velocity v) {
        this.c = new Circle(new Point(x, y), r);
        this.color = color;
        this.velocity = v;
        this.ge = new GameEnvironment();
    }

    public Ball(Point center, int r, Color color, int width, int height, GameEnvironment ge) {
        this.c = new Circle(center, r);
        this.color = color;
        this.velocity = null;
        this.winHeight = height;
        this.winWidth = width;
        this.ge = ge;
    }

    public Ball(double x, double y, int r, java.awt.Color color, int width, int height, GameEnvironment ge) {
        this.c = new Circle(new Point(x, y), r);
        this.color = color;
        this.velocity = null;
        this.winHeight = height;
        this.winWidth = width;
        this.ge = ge;
    }

    public Ball(double x, double y, int r, java.awt.Color color, Velocity v, GameEnvironment ge) {
        this.c = new Circle(new Point(x, y), r);
        this.color = color;
        this.velocity = v;
        this.ge = ge;
    }

    // accessors
    public int getX() {
        return (int)this.c.getCenter().getX();
    }
    public int getY() {
        return (int)this.c.getCenter().getY();
    }
    public int getSize() {
        return this.c.getRadius();
    }
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int)c.getCenter().getX(), (int)c.getCenter().getY(), c.getRadius()); // Filling the circle itself with 'color'.
        surface.setColor(Color.BLACK);
        surface.drawCircle((int)c.getCenter().getX(), (int)c.getCenter().getY(), c.getRadius()); // Drawing the outline of the circle.
    }

    @Override
    public void timePassed() {
        moveOneStep();
        // Change graphical effect? (double points event).
        // Todo be creative
    }

    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }
    public void setVelocity(Velocity v) { velocity = v; }
    public Velocity getVelocity() {
        return velocity;
    }

    public void moveOneStep() {
        if (velocity == null) return;
        CollisionInfo ci;
        Line line = new Line(c.getCenter(), velocity.applyToPoint(c.getCenter()));
        boolean foundCollision = false;
        Velocity afterCollision = null;
        // new
        while ((ci = ge.getClosestCollision(line)) != null) {
            line.setEnd(line.middle());
            if (!foundCollision) {
                afterCollision = ci.collisionObject().hit(this, ci.collisionPoint() ,velocity);
                foundCollision = true;
            }
        }
        if (foundCollision) {
            setVelocity(afterCollision);
        }
        c.setCenter(line.end());
        // todo IF OUT OF RANGE -> DELETE ITSELF FROM LIST.

        double absolute_speed = Math.sqrt((velocity.xAxis * velocity.xAxis) + (velocity.yAxis * velocity.yAxis));
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

    public void removeFromGame(Game g) {
        if (g != null) {
            g.removeSprite(this);
            // Remove ball counter.
        }
    }
}