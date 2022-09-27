package ArkanoidClasses;

import Collidables.*;
import Geometry.Point;
import Interfaces.*;
import biuoop.*;
import java.awt.*;
import java.util.Random;

public class Game {
    private int w;
    private int h;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    private Paddle paddle; // Could've implement array of paddles. need to save here as variable to let the run method use it.


    public Game(int width, int height) {
        this.w = width;
        this.h = height;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new GUI("Arkanoid - The ArkanoidClasses.Game", w, h);
        sleeper = new Sleeper();
    }

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Collidables.Ball (and Collidables.Paddle)
    // and add them to the game.
    public void initialize() {
        Random r = new Random();
        Block[] blocks = new Block[] {
                // Borders :
                new Block(0, 0, 20, 600),
                new Block(780, 0, 20, 600),
                new Block(0, 0, 800, 20),
                // new Collidables.Block(0, 580, 800, 20) // Not using the bottom one..
                // Other blocks (Geometry.Point blocks, more borders..)
        };

        Ball[] balls = new Ball[] {
                new Ball(new Point((w - Double.MIN_VALUE) * r.nextDouble(),
                        (h - Double.MIN_VALUE) * r.nextDouble()), 10, Color.yellow, w, h, environment)
        };
        paddle = new Paddle(gui.getKeyboardSensor(), 100, 20, w, h,0, w, 7);
        paddle.addToGame(this);
        for (Block block : blocks) {
            block.addToGame(this);
        }
        for (Ball b : balls) {
            b.addToGame(this);
            // Speeds between 1 and 20.
            // b.setVelocity(Collidables.Velocity.fromAngleAndSpeed(360 * r.nextDouble(), 1 + (19 * r.nextDouble())));
            b.setVelocity(Velocity.fromAngleAndSpeed(360 * r.nextDouble(), 5)); // test
        }

    }

    // Run the game -- start the animation loop.
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            paddle.checkMovement();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}