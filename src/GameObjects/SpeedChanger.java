package GameObjects;

import GameObjects.Ball;
import GameObjects.Block;
import Interfaces.HitListener;

public class SpeedChanger implements HitListener {
    double change;
    public SpeedChanger(double change) {
        this.change = change > 0 ? change : Double.MIN_VALUE;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        double newDx = hitter.getVelocity().getxAxis() * change;
        double newDy = hitter.getVelocity().getyAxis() * change;
        hitter.getVelocity().setxAxis(newDx);
        hitter.getVelocity().setyAxis(newDy);
    }
}
