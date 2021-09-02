import org.newdawn.slick.Input;

/**
 * The Class Player.
 * 
 * @author Ziqi Jia
 * @Description: An entity that represents the player, controlled by the user.
 */
public class Player extends Sprite
{

    /** The image path of the player. */
    private static final String PLAYER_PATH = "assets/frog.png";

    /** The initial x-coordinate of the player. */
    private static final float INITIAL_X = 512;

    /** The initial y-coordinate of the player. */
    private static final float INITIAL_Y = 720;

    /** Flag to check if the player is ride on the log or turtles. */
    private boolean isRide = false;

    /** Flag to check if the player lost a life. */
    private boolean isLostLife;

    /** The momentum that add to the player when riding on the rideable object. */
    private float momentum = 0;

    /** The x pos of the player. */
    private float xPos;

    /** The y pos of the player. */
    private float yPos;

    /**
     * Instantiates a new player.
     */
    public Player()
    {
        super(PLAYER_PATH, INITIAL_X, INITIAL_Y);
    }

    /**
     * {@inheritDoc}
     * 
     * @see Sprite#update(org.newdawn.slick.Input, int)
     */
    @Override
    public void update(Input input, int delta)
    {
        doMovement(input);
        chechLostLife();
    }

    /**
     * The player's movement.
     *
     * @param input The Slick input object.
     */
    private void doMovement(Input input)
    {
        // handle horizontal and vertical movement
        float dx = 0, dy = 0;
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            dx -= World.TILE_SIZE;
        }
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            dx += World.TILE_SIZE;
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            dy += World.TILE_SIZE;
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            dy -= World.TILE_SIZE;
        }

        // add momentum to its movement when riding on the rideable objects.
        move(dx + momentum, dy);
        momentum = 0;
        isRide = false;
        // record the player's x and y position
        xPos = dx;
        yPos = dy;

        // Force the player clamp to the screen.
        clampToScreen();
        // If the player oﬀ the screen, the player should lose a life
        if (getX() > App.SCREEN_WIDTH || getX() < 0) {
            resetPosition();
            World.getInstance().lifeLost();
        }
    }

    /**
     * {@inheritDoc} The behaviour of the player When it make contact with the hazard object, the player will lose a
     * life.
     * 
     * @see Sprite#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        if (other.isHazard() == true && isRide == false) {
            resetPosition();
            isLostLife = true;
        }
    }

    /**
     * Check if the player lost a life, call lifelost method from the world to reduce the lives.
     */
    private void chechLostLife()
    {
        if (isLostLife) {
            World.getInstance().lifeLost();
            isLostLife = false;
        }
    }

    /**
     * {@inheritDoc} Reset the player's position to its initial position if it is on contact with the hazard object.
     * 
     * @see Sprite#resetPosition()
     */
    @Override
    public void resetPosition()
    {
        setX(INITIAL_X);
        setY(INITIAL_Y);
    }

    /**
     * Sets the ride's value.
     *
     * @param ride the new ride
     */
    public void setRide(boolean ride)
    {
        this.isRide = ride;
    }

    /**
     * Sets the momentum from the rideable object's x position.
     *
     * @param momentum the new momentum
     */
    public void setMomentum(float momentum)
    {
        this.momentum = momentum;
    }

    /**
     * Gets the x pos.
     *
     * @return the xPos
     */
    public float getxPos()
    {
        return xPos;
    }

    /**
     * Sets the x pos.
     *
     * @param xPos the xPos to set
     */
    public void setxPos(float xPos)
    {
        this.xPos = xPos;
    }

    /**
     * Gets the y pos.
     *
     * @return the yPos
     */
    public float getyPos()
    {
        return yPos;
    }

    /**
     * Sets the y pos.
     *
     * @param yPos the yPos to set
     */
    public void setyPos(float yPos)
    {
        this.yPos = yPos;
    }

    /**
     * @return the playerPath
     */
    public static String getPlayerPath()
    {
        return PLAYER_PATH;
    }

}
