package GameObjects;

import ArkanoidClasses.Game;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Counter;
import Interfaces.HitListener;

// Spike blocks, and also special death block.
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    public BlockRemover(Game game) {
        this.game = game;
        remainingBlocks = new Counter(0);
    }

    public void incBlocks(int num) {
        remainingBlocks.increase(num);
    }
    public void decBlocks(int num) {
        remainingBlocks.decrease(num);
    }


    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}