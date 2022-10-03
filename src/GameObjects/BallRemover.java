package GameObjects;

import ArkanoidClasses.Game;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Counter;
import Interfaces.HitListener;

public class BallRemover implements HitListener {
    private Game g;
    Counter remainingBalls;

    public BallRemover(Game game, Counter remainingBalls) {
        this.g = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("Ball was removed");
        hitter.removeFromGame(g);
        remainingBalls.decrease(1);
    }
}
