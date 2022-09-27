package Collidables;

import ArkanoidClasses.Game;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.*;
import biuoop.*;

public class Paddle implements Sprite, Collidable {
    private int movementFactor = 5;
    private Rectangle rec;
    private biuoop.KeyboardSensor keyboard;
    private int borderRight;
    private int borderLeft;

    public Paddle(KeyboardSensor keyboard, int recWidth, int recHeight, int winWidth, int winHeight, int borderLeft, int borderRight) {
        // Default - half the screen.
        recWidth = recWidth > (0.5 * winWidth) ?  (int) (0.5 * winWidth) : recWidth;
        recHeight = recHeight > (0.5 * winHeight) ?  (int) (0.5 * winHeight) : recHeight;

        this.borderRight = borderRight;
        this.borderLeft = borderLeft;

        double startingX = (winWidth - recWidth) / 2.0;
        double startingY = (winHeight - recHeight);
        rec = new Rectangle(new Point(startingX, startingY), recWidth, recHeight);
        this.keyboard = keyboard;
    }
    public Paddle(KeyboardSensor keyboard, int recWidth, int recHeight, int winWidth, int winHeight, int borderLeft, int borderRight, int movement) {
        // Default - half the screen.
        recWidth = recWidth > (0.5 * winWidth) ?  (int) (0.5 * winWidth) : recWidth;
        recHeight = recHeight > (0.5 * winHeight) ?  (int) (0.5 * winHeight) : recHeight;

        this.borderRight = borderRight;
        this.borderLeft = borderLeft;

        double startingX = (winWidth - recWidth) / 2.0;
        double startingY = (winHeight - recHeight);
        rec = new Rectangle(new Point(startingX, startingY), recWidth, recHeight);
        this.keyboard = keyboard;
        this.movementFactor = movement;
    }

    public void moveLeft() {
        double newX = rec.getUpperLeft().getX() - movementFactor;
        if (newX < borderLeft) {
            newX = borderLeft;
        }
        rec.getUpperLeft().setX(newX);
    }
    public void moveRight() {
        double newX = rec.getUpperLeft().getX() + movementFactor;
        if (newX + rec.getWidth() > borderRight) {
            newX = borderRight - rec.getWidth();
        }
        rec.getUpperLeft().setX(newX);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(), (int) rec.getWidth(), (int) rec.getHeight());
    }

    // Interfaces.Sprite
    public void timePassed() {
        // Change graphical effect? (special event / special blocks with special scores)
        // Todo be creative
    }

    // Interfaces.Collidable
    public Rectangle getCollisionRectangle() {
        return rec;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // same object
        if (collisionPoint.getY() == rec.getUpperLeft().getY() || collisionPoint.getY() == rec.getUpperLeft().getY() + rec.getHeight()) {
            currentVelocity.setyAxis(currentVelocity.getyAxis() * (-1));
        }
        if (collisionPoint.getX() == rec.getUpperLeft().getX() || collisionPoint.getX() == rec.getUpperLeft().getX() + rec.getWidth()) {
            currentVelocity.setxAxis(currentVelocity.getxAxis() * (-1));
        }
        return currentVelocity;
        // todo - add a different angle for every section of of collision on the paddle - if on the very right side - 60 degrees, left side - 300 degrees, middle - 0 degrees, and 30 and 330 respectively. 5 regions total.
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public void checkMovement() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
}