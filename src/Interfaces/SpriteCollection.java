package Interfaces;

import java.util.List;
import java.util.ArrayList;

import Interfaces.Sprite;
import biuoop.DrawSurface;

public class SpriteCollection {
    List<Sprite> sprites;

    public SpriteCollection() {
        sprites = new ArrayList<>();
    }
    public SpriteCollection(int size) {
        sprites = new ArrayList<>(size);
    }
    public void addSprite(Sprite s) {
        if (s != null) sprites.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}