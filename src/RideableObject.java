import org.newdawn.slick.Input;

/**
 * The Class RideableObject.
 * 
 * @author Ziqi Jia
 * @Description: An abstract Entity that holds information and behaviours for a rideable object. The detailed
 *               implementation is done in subclasses.
 */
public abstract class RideableObject extends Sprite
{

    /** The moving direction of the rideable object. */
    private boolean moveRight;

    /** The speed of the rideable object. */
    private float speed;

    /** The x position of the rideable object. */
    private float xPos;

    /**
     * Gets the initial x-coordinate of the vehicle.
     *
     * @return the initial X
     */
    private final float getInitialX()
    {
        return moveRight ? -World.TILE_SIZE / 2 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
    }

    /**
     * Instantiates a new rideable object.
     *
     * @param imageSrc the image source of the vehicle.
     * @param x The x-coordinate of the vehicle.
     * @param y The y-coordinate of the vehicle.
     * @param moveRight the moving direction.
     * @param moveRight the move right
     */
    public RideableObject(String imageSrc, float x, float y, boolean moveRight)
    {
        super(imageSrc, x, y);
        this.moveRight = moveRight;

    }

    /**
     * {@inheritDoc} Update the information regarding the movement of the rideable object.
     * 
     * @see Sprite#update(org.newdawn.slick.Input, int)
     */
    @Override
    public void update(Input input, int delta)
    {
        spriteBehaviour(delta, speed);
        resetPosition();
    }

    /**
     * {@inheritDoc} Detailed behaviour once the rideable object is moving. Details vary depending on the rideable
     * object type.
     * 
     * @see Sprite#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        move(speed * delta * (moveRight ? 1 : -1), 0);
        xPos = speed * delta * (moveRight ? 1 : -1);

    }

    /**
     * {@inheritDoc} Reset the vehicle's position to its initial position, if the vehicle has moved off the screen.
     * 
     * @see Sprite#resetPosition()
     */
    @Override
    public void resetPosition()
    {
        if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
            || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
            setX(getInitialX());
        }
    }

    /**
     * {@inheritDoc} the behaviour of the rideable object when it make contact with other sprite.
     * 
     * @see Sprite#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        // when the rideable object is in contact with player, the player should ride on it and move with it.
        if (other instanceof Player) {
            ((Player) other).setMomentum(xPos);
            ((Player) other).setRide(true);
        }

        // the extralife should move with the rideable object.
        if (other instanceof ExtraLife) {
            ((ExtraLife) other).setMomentum(xPos);
        }
    }

    /**
     * Gets the speed of the rideable object.
     *
     * @return the speed
     */
    public float getSpeed()
    {
        return speed;
    }

    /**
     * Sets the speed of the rideable object.
     *
     * @param speed the speed to set
     */
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return the dx
     */
    public float getxPos()
    {
        return xPos;
    }

    /**
     * Checks if is move right.
     *
     * @return the moveRight
     */
    public boolean isMoveRight()
    {
        return moveRight;
    }

    /**
     * Sets the move right.
     *
     * @param moveRight the moveRight to set
     */
    public void setMoveRight(boolean moveRight)
    {
        this.moveRight = moveRight;
    }

}
