package GameObjects;

import ArkanoidClasses.Game;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Counter;
import Interfaces.HitListener;

public class ScoreTrackingListener implements HitListener {
    private Game game; // This is for maybe displaying highest score and strings 'you achieved highest score'.
    private Counter currentScore;

    public ScoreTrackingListener(Game g, Counter scoreCounter) {
        game = g;
        currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(beingHit.getValue());
    }
}