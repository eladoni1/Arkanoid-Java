package GameObjects;

import ArkanoidClasses.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection sprites;
    public PauseScreen(KeyboardSensor k, SpriteCollection sprites) {
        this.keyboard = k;
        this.stop = false;
        this.sprites = sprites;
    }
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        String s = "paused -- press space to continue";
        d.drawText(d.getWidth() / 2 - (s.length() * 8), d.getHeight() / 2, s, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    public boolean shouldStop() { return this.stop; }
}