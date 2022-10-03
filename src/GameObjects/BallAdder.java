package GameObjects;

import ArkanoidClasses.Game;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Counter;
import Geometry.Point;
import Interfaces.HitListener;

import java.awt.*;
import java.util.Random;

public class BallAdder implements HitListener {
    private Game g;
    private Counter remainingBalls;

    public BallAdder(Game game, Counter remainingBalls) {
        this.g = game;
        this.remainingBalls = remainingBalls;
    }

    // Can make the following function abstract and on construction add a 'hitEvent' function.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Random r = new Random();
        double angle = Double.MIN_VALUE +  90.0 + r.nextDouble() * 180;
        if (angle >= 270) {
            angle = 270 - Double.MIN_VALUE;
        }
        Ball b = new Ball(new Point(g.getWidth() / 2.0, g.getHeight() / 2.0), g.getDefaultRadius(), Color.magenta, g.getWidth(), g.getHeight(), g.getEnvironment());
        b.setVelocity(Velocity.fromAngleAndSpeed(angle,
                Math.sqrt(g.getDefaultVelocityDx() * g.getDefaultVelocityDx() +
                        g.getDefaultVelocityDy() * g.getDefaultVelocityDy())));
        b.addToGame(g);
        remainingBalls.increase(1);
    }
}
