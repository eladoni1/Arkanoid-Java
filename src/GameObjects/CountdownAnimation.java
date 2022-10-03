package GameObjects;

import ArkanoidClasses.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;

public class CountdownAnimation implements Animation {
    private SpriteCollection sprites;
    private int startFrom;

    private double waitForEach;
    private boolean tookAlready;
    private long startTime;
    private boolean shouldStop = false;
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        startFrom = countFrom;
        this.sprites = gameScreen;
        waitForEach = 1000 * numOfSeconds / (countFrom + 1);
        tookAlready = false;
    }
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        if (!tookAlready) {
            startTime = System.currentTimeMillis();
            tookAlready = true;
        }
        long curr = System.currentTimeMillis() - startTime;
        int skip = (int) (curr / waitForEach);
        if (skip > startFrom) {
            shouldStop = true;
        } else {
            if (skip == startFrom) {
                d.drawText(50, d.getHeight() / 2, "GO!!!", 32);
            } else {
                d.drawText(50, d.getHeight() / 2, "" + (startFrom - skip) + "..", 32);
            }
        }

    }

    public boolean shouldStop() {
        return shouldStop;
    }
}