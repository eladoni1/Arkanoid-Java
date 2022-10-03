package ArkanoidClasses;

import GameObjects.*;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.*;
import biuoop.*;
import java.awt.*;
import java.util.Random;

public class Game implements Animation {
    private Block background;
    private Block specialDeathBlock;
    private Counter ballCounter;
    private Counter blockCounter;
    private int w;
    private int h;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private int defaultRadius;
    private double defaultVelocityDx;
    private double defaultVelocityDy;
    private Counter scoreCounter;
    private int PlayableLeftWidth;
    private int PlayableRightWidth;
    private int PlayableUpperHeight;
    private int PlayableLowerHeight;

    private Paddle paddle; // Could've implement array of paddles. need to save here as variable to let the run method use it.
    private boolean running;
    private AnimationRunner runner;
    private int fps;
    private KeyboardSensor keyboard;


    public Game(int width, int height) { // General.
        fps = 0;
        runner = null;
        running = false;
        PlayableLeftWidth = 0;
        PlayableRightWidth = width;
        PlayableUpperHeight = 0;
        PlayableLowerHeight = height;
        this.scoreCounter = new Counter(0);
        this.specialDeathBlock = null;
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.w = width;
        this.h = height;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new GUI("Arkanoid - The ArkanoidClasses.Game", w, h);
        keyboard = gui.getKeyboardSensor();
        sleeper = new Sleeper();
    }



    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    public void removeSprite(Sprite s) {
        // Could implement .equals()?
        sprites.removeSprite(s);
    }
    public void removeCollidable(Collidable c){
        // Could implement .equals()?
        environment.removeCollidable(c);
    }

    // Initialize a new game: create the Blocks and Collidables.Ball (and Collidables.Paddle)
    // and add them to the game.
    public void initialize() { // Specific.

        // Defaults.
        this.defaultRadius = 10;
        this.defaultVelocityDx = 5;
        this.defaultVelocityDy = 5;

        // HitListeners initialize.
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallAdder ballAdder = new BallAdder(this, ballCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this, scoreCounter);

        // Random for random speeds, angle and position.
        Random r = new Random();

        // Background
        this.background = new Block(0, 0, w, h, new Color(42, 130, 21), 0);
        // Instead of adding it /w 'block.addToGame(this)' to collision as well, just add it to sprites.
        sprites.addSprite(background);


        Block[] blocks = new Block[] {
                // Borders :
                new Block(0, 0, 20, 600, Color.pink, 10),
                new Block(780, 0, 20, 600, Color.pink, 10),
                new Block(0, 0, 800, 50, Color.pink, 10),
                // new Collidables.Block(0, 580, 800, 20) // Not using the bottom one..
                // Other blocks (Geometry.Point blocks, more borders..)
        };


        // Paddle
        paddle = new Paddle(keyboard, 100, 20, w, h,0, w, 7);
        paddle.addToGame(this);

        // Min and Max for randomize for ball added.
        double minWidth = Integer.MAX_VALUE, maxWidth = Integer.MIN_VALUE,
                minHeight = Integer.MAX_VALUE, maxHeight = h;

        // Blocks added to game and added to counter of blocks exist.
        for (Block block : blocks) {
            blockCounter.increase(1);
            block.addToGame(this);
            block.addHitListener(scoreTracker);
            Point p = block.getCollisionRectangle().getUpperLeft();
            double curr_height = block.getCollisionRectangle().getHeight(),
                    curr_width = block.getCollisionRectangle().getWidth();

            // Updating the Max and Min playable surfaces where balls can 'spawn' in.
            minHeight = Math.min(minHeight, p.getY() + curr_height);
            minWidth = Math.min(minWidth, p.getX() + curr_width);
            maxWidth = Math.max(maxWidth, p.getX());

            // block.addHitListener(ballAdder);
            // block.addHitListener(blockRemover);
        }
        System.out.println("maxHeight" + maxHeight + "maxWidth" + maxWidth + "minHeight" + minHeight + "minWidth" + minWidth);

        // Scoreboard
        ScoreIndicator scoreBoard = new ScoreIndicator(new Rectangle(0, 0, w, 35), scoreCounter, 20);
        sprites.addSprite(scoreBoard);

        Ball[] balls = new Ball[] {
                new Ball(new Point(minWidth + (maxWidth - minWidth) * r.nextDouble(),
                        minHeight + (maxHeight - minHeight) * r.nextDouble()), defaultRadius, Color.yellow, w, h, environment),
                new Ball(new Point(minWidth + (maxWidth - minWidth) * r.nextDouble(),
                        minHeight + (maxHeight - minHeight) * r.nextDouble()), defaultRadius, Color.red, w, h, environment),
                new Ball(new Point(minWidth + (maxWidth - minWidth) * r.nextDouble(),
                        minHeight + (maxHeight - minHeight) * r.nextDouble()), defaultRadius, Color.blue, w, h, environment)
        };
        int maxRadius = 0;
        for (Ball b : balls) {
            maxRadius = Math.max(b.getSize(), maxRadius);
        }

        // Balls added to game and added to counter of balls exist.
        for (Ball b : balls) {
            ballCounter.increase(1);
            b.addToGame(this);
            b.setVelocity(Velocity.fromAngleAndSpeed(90 + 180 * r.nextDouble(), 5)); // todo to be changed to random (test purposes).
            // Random speeds between 1 and 20.
            // b.setVelocity(Collidables.Velocity.fromAngleAndSpeed(360 * r.nextDouble(), 1 + (19 * r.nextDouble())));
        }

        specialDeathBlock = new Block(new Point(-maxRadius, -maxRadius), maxRadius * 2 + w, maxRadius * 2 + h);
        specialDeathBlock.addHitListener(new BallRemover(this, ballCounter));
        // Instead of adding it /w 'block.addToGame(this)' to appearance as well, just add it to collidable.
        environment.addCollidable(specialDeathBlock);

        // Creating animation runner to be seperate from game.
        fps = 60;
        runner = new AnimationRunner(gui, fps, sleeper);

    }

    // Run the game -- start the animation loop.
    public void run() {
        this.runner.run(new CountdownAnimation(4, 3, sprites));
        //...
        running = true;
        // ...
        this.runner.run(this);

    }

    public Counter getBallCounter() { return ballCounter; }
    public Counter getBlockCounter() {
        return this.blockCounter;
    }
    public int getWidth() {
        return w;
    }
    public int getHeight() {
        return h;
    }
    public int getDefaultRadius() {
        return defaultRadius;
    }
    public double getDefaultVelocityDx() {return defaultVelocityDx;}
    public double getDefaultVelocityDy() {return defaultVelocityDy;}
    public GameEnvironment getEnvironment(){return environment;}

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (running) {
            paddle.checkMovement();
            if (this.keyboard.isPressed("p")) {
                this.runner.run(new PauseScreen(this.keyboard, sprites));
            }
            if (blockCounter.getValue() <= 0 || ballCounter.getValue() <= 0) {
                running = false;
                gui.close();
            }
            this.sprites.notifyAllTimePassed();
            return;
        }
        // Todo DrawSurface.drawText(); 'you win' or something along these lines.
        if (blockCounter.getValue() <= 0) {
            // You Win
        }
        if (ballCounter.getValue() <= 0) {
            // You Lose
        }
        // gui.close();
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }


    // Remove
    public class PrintingHitListener implements HitListener {
        public void hitEvent(Block beingHit, Ball hitter) {
            System.out.println("A Block was hit.");
        }
    }
    // Remove
}

