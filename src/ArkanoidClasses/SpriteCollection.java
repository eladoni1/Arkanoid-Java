package ArkanoidClasses;

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
    public void removeSprite(Sprite s) { if (s!= null) sprites.remove(s); }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite s : spritesCopy) {
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