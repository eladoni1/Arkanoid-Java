package GameObjects;

import Geometry.Rectangle;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    // Todo creativity with Color c;, and changing it during timePassed (to indicate special point events, or different levels).
    Rectangle scoreBoard;
    Counter score;
    int fontSize;
    public ScoreIndicator(Rectangle scoreRectangle, Counter scoreCounter, int size) {
        scoreBoard = scoreRectangle;
        score = scoreCounter;
        fontSize = size;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle((int) scoreBoard.getUpperLeft().getX(), (int)scoreBoard.getUpperLeft().getY(),
                (int)scoreBoard.getWidth(), (int)scoreBoard.getHeight());
        // Drawing score.
        d.setColor(Color.BLACK);
        String s = "Your Score: " + score.getValue() + "";
        int widthOfText = fontSize * s.length();
        d.drawText((int) (scoreBoard.getUpperLeft().getX() + (scoreBoard.getWidth() / 2)),
                (int) (scoreBoard.getUpperLeft().getY() + (scoreBoard.getHeight() / 2)), s, fontSize);
    }

    @Override
    public void timePassed() {
        // Do nothing (no need to shift color?)
    }
}
