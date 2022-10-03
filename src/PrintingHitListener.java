import GameObjects.Ball;
import GameObjects.Block;
import Interfaces.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}