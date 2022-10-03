package Interfaces;

import ArkanoidClasses.SpriteCollection;
import biuoop.DrawSurface;

public interface Animation {
    // SpriteCollection sprites = new SpriteCollection();
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}