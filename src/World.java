import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The Class World.
 * 
 * @author Ziqi Jia
 * @Description: Class to manage all the various objects in the game.
 */
public class World
{

    /** The image path of lives. */
    private static final String LIVES_PATH = "assets/lives.png";

    /** The Constant tile size of the world. */
    public static final int TILE_SIZE = 48;

    /** The Constant initial lives if the player. */
    private static final int INITIAL_LIVES = 3;

    /** The lives image. */
    private Image livesImage;

    /** The current live number of the player. */
    private int lives = INITIAL_LIVES;

    /** The level of the game. */
    private int level = 0;

    /** The number of file that has been read. */
    private int numberOfFile = 0;

    /** The Constant appear time of the extra life. */
    private static final int APPEAR_TIME = 14000;

    /** The timer to record the initial time of each extralife's lifecycle. */
    private int initialTimer = 0;

    /** The timer of the world. */
    private static int timer;

    /** The wait time of the extra life. */
    private int waitTime;

    /** The primary Sprite array that store all Sprite. */
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /** The primary RideableObject array that store all log and longlog objects. */
    private static ArrayList<RideableObject> LogAndLongLog = new ArrayList<>();

    /** The random variable that used to select random number or element. */
    private Random random = new Random();

    /** The class variable to store a extralife. */
    private ExtraLife extraLife;

    /** The class variable to store a random log that selected from LogAndLongLog arraylist. */
    private RideableObject randomLog;

    /** The left x position that reverse extra life's moving direction. */
    private float left_X_Reverse;

    /** The right x position that reverse extra life's moving direction. */
    private float right_X_Reverse;

    /** The class variable to store a world. */
    private static World world;

    /**
     * Get instance of class world variable.
     * 
     * @return the world
     */
    public static World getInstance()
    {
        if (world == null) {
            world = new World();
        }
        return world;
    }

    /**
     * Adds the log and longLog object to the LogAndLongLog arrayList.
     *
     * @param rider the rider
     */

    public void addLog(RideableObject rideableObject)
    {
        LogAndLongLog.add(rideableObject);
    }

    /**
     * Adds the sprite to the world instance's primary sprites arrayList.
     *
     * @param sprite the sprite
     */
    public void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }

    /**
     * Remove the sprite from the world instance's primary sprites arrayList.
     *
     * @param sprite the sprite
     */
    public void removeSprite(Sprite sprite)
    {
        sprites.remove(sprite);
    }

    /**
     * Instantiates all the sprite and create the background.
     */
    public World()
    {
        try {
            livesImage = new Image(LIVES_PATH);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        world = this;

        // Create sprites in Levels class by reading a level file.
        Levels.readLevelFormat("assets/levels/0.lvl");
        numberOfFile++;
        // create player
        addSprite(new Player());

    }

    /**
     * Updates the world's representation of objects.
     *
     * @param input the Slick input object
     * @param delta the number of milliseconds since the last frame
     */
    public void update(Input input, int delta)
    {
        // timer have to be after speed up since extralife and turtle uses timer
        timer += delta;
        // update all sprites
        for (int i = 0; i < sprites.size(); ++i) {
            sprites.get(i).update(input, delta);
        }
        // update all log and longlog
        for (int i = 0; i < LogAndLongLog.size(); ++i) {
            LogAndLongLog.get(i).update(input, delta);
        }

        // loop over all pairs of sprites and test for intersection
        for (Sprite sprite1 : sprites) {
            for (Sprite sprite2 : sprites) {
                if (sprite1 != sprite2 && sprite2.getActive() && sprite1.collides(sprite2)) {
                    sprite1.onCollision(sprite2);
                }
            }
        }
        // update extra life
        addExtraLife();
        // Clean up inactive sprites
        for (int i = 0; i < sprites.size(); ++i) {
            if (sprites.get(i).getActive() == false) {
                // remove inactive sprite from array list
                sprites.remove(i);
                // decrement counter to make sure we don't miss any
                i--;
            }
        }

        // Can exit the game
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
    }

    /**
     * Renders all game objects to the screen.
     *
     * @param g the graphics object to use
     */
    public void render(Graphics g)
    {
        // Draw all sprites
        for (Sprite sprite : sprites) {
            sprite.render();
        }
        // Draw the lives image.
        for (int i = 1; i <= lives; ++i) {
            livesImage.drawCentered(24 + 32 * (i - 1), 744);
        }
    }

    /**
     * Adds the extra life to the world.
     */
    public void addExtraLife()
    {
        waitTime = (random.nextInt(11) + 25);
        randomLog = LogAndLongLog.get(random.nextInt(LogAndLongLog.size()));
        // left and right x-coordinate that reverse the extralife's moving direction.
        left_X_Reverse = -randomLog.getImage().getWidth() / 2 + World.TILE_SIZE;
        right_X_Reverse = +randomLog.getImage().getWidth() / 2 + World.TILE_SIZE;

        // create extra life for 14 seconds after a random waiting time
        if (timer - initialTimer > waitTime * 1000 && timer - initialTimer < APPEAR_TIME + waitTime * 1000) {
            if (ExtraLife.present == false) {
                extraLife = new ExtraLife(randomLog.getX(), randomLog.getY(), randomLog.isMoveRight());
                addSprite(extraLife);
            }
        }
        // remove the extra life from array list after 14 seconds
        if (timer - initialTimer > APPEAR_TIME + waitTime * 1000) {
            if (ExtraLife.present) {
                removeSprite(extraLife);
                ExtraLife.present = false;
            }
            initialTimer = timer;
        }
    }

    /**
     * Called whenever the player loses a life.
     */
    public void lifeLost()
    {
        lives--;
        if (lives == 0) {
            System.exit(0);
        }
    }

    /**
     * Called whenever the player add a life.
     */
    public void addLife()
    {
        lives++;
    }

    /**
     * @return the lives
     */
    public int getLives()
    {
        return lives;
    }

    /**
     * @param lives the lives to set
     */
    public void setLives(int lives)
    {
        this.lives = lives;
    }

    /**
     * Gets the current level.
     *
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Sets the current level.
     *
     * @param level the new level
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * Change to the first level of the game.
     */
    public void changeLevel()
    {
        if (numberOfFile == 1) {
            sprites.clear();
            LogAndLongLog.clear();
        }
        // world = this;
        getInstance();
        // Create sprites in Levels class by reading a level file.
        Levels.readLevelFormat("assets/levels/1.lvl");
        // creade player
        addSprite(new Player());
    }

    /**
     * Gets the timer.
     *
     * @return the timer
     */
    public static int getTimer()
    {
        return timer;
    }

    public float getLeft_X_Reverse()
    {
        return left_X_Reverse;
    }

    public float getRight_X_Reverse()
    {
        return right_X_Reverse;
    }

    /**
     * @return the waitTime
     */
    public int getWaitTime()
    {
        return waitTime;
    }

}
