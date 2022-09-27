import ArkanoidClasses.Game;
import ArkanoidClasses.GameEnvironment;
import Collidables.Ball;
import Collidables.Block;
import Collidables.Velocity;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.*;

import java.awt.Color;
import java.util.List;

public class SimpleGuiExample {

    public void drawRandomCircles() {
        int width = 400, height = 400;
        GUI gui = new GUI("Random Circles Example", width, height);
        Sleeper sleeper = new Sleeper();
        GameEnvironment ge = new GameEnvironment();
        Ball b = new Ball(new Point(150, 150), 10, Color.yellow, width, height, ge);
        b.setVelocity(Velocity.fromAngleAndSpeed(315, 5));
        Block block1 = new Block(0, 0, 20, 400);
        Block block2 = new Block(380, 0, 20, 400);
        Block block3 = new Block(0, 0, 400, 20);
        Block block4 = new Block(0, 380, 400, 20);
        ge.addCollidable(block1);
        ge.addCollidable(block2);
        ge.addCollidable(block3);
        ge.addCollidable(block4);
        while(true) {
            DrawSurface d = gui.getDrawSurface();
            block1.drawOn(d);
            block2.drawOn(d);
            block3.drawOn(d);
            block4.drawOn(d);
            b.drawOn(d);
            gui.show(d);
            b.moveOneStep();
            sleeper.sleepFor(50);
        }
    }

    public void simpleTest() {
        Rectangle rec = new Rectangle(new Point(0, 380), 400, 20);
        List<Point> a = rec.intersectionPoints(new Line(new Point(379.809703885629, 378.0419369326606), new Point(376.27416997969624, 381.5774708385933)));
        for (Point p : a) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
//        SimpleGuiExample example = new SimpleGuiExample();
//        example.drawRandomCircles();
//        example.simpleTest();
        Game g = new Game(800, 600);
        g.initialize();
        g.run();
    }
}