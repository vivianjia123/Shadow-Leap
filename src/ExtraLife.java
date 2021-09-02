import org.newdawn.slick.Input;

/**
 * The Class ExtraLife.
 * 
 * @author Ziqi Jia
 * @Description: An extra life object that gives the player an extra life.
 */
public class ExtraLife extends RideableObject
{

    /** The image path of the extra life. */
    private static final String EXTRALIFE_IMAGE = "assets/extralife.png";

    /** The Constant time of the extra life when it moves one tile every 2 seconds. */
    public static final int MOVE_TIME = 2000;

    /** The momentum that add to extra life when it ride on the log. */
    private float momentum = 0;

    /** The timer represents the initial time when extra life move. */
    // private int movingTimer = 0;

    /** The moving direction of the Extralife. */
    // private boolean direction;

    /** Flag to determine if the present extralife is shown in the world. */
    public static boolean present = false;

    /**
     * Instantiates a new extra life.
     *
     * @param x The x-coordinate of the vehicle.
     * @param y The y-coordinate of the vehicle.
     * @param moveRight the moving direction
     */
    public ExtraLife(float x, float y, boolean moveRight)
    {
        super(EXTRALIFE_IMAGE, x, y, moveRight);
        present = true;
        // direction = true;
    }

    /**
     * {@inheritDoc} Update the information regarding the movement of the extra life object.
     * 
     * @see RideableObject#update(org.newdawn.slick.Input, int)
     */
    @Override
    public void update(Input input, int delta)
    {
        spriteBehaviour();
        resetPosition();

    }

    /**
     * {@inheritDoc} Detailed behaviour once the extra life is moving.
     * 
     * @see RideableObject#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour()
    {
        float dx = 0;
        // if (World.getTimer() - World.getInstance().getWaitTime() - movingTimer < MOVE_TIME) {
        // } else {
        // if (direction) {
        // if (getX() + dx < World.getInstance().getRight_X_Reverse()) {
        // dx += World.TILE_SIZE;
        // } else {
        // direction = false;
        // dx -= World.TILE_SIZE;
        // }
        // } else {
        // if (getX() + dx > World.getInstance().getLeft_X_Reverse()) {
        // dx -= World.TILE_SIZE;
        // } else {
        // direction = true;
        // dx += World.TILE_SIZE;
        // }
        // }
        // movingTimer = World.getTimer();
        // }

        move(dx + momentum, 0);
        momentum = 0;
    }

    /**
     * {@inheritDoc} The behaviour of the extralife When it make contact with the player, the player will get an extra
     * life and the extralife will deactivate.
     * 
     * @see RideableObject#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        if (other instanceof Player) {
            World.getInstance().addLife();
            this.deactivate();
        }
    }

    /**
     * Gets the momentum.
     *
     * @return the momentum
     */
    public float getMomentum()
    {
        return momentum;
    }

    /**
     * Sets the momentum.
     *
     * @param momentum the new momentum
     */
    public void setMomentum(float momentum)
    {
        this.momentum = momentum;
    }

}
