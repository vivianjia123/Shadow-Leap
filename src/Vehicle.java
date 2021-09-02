import org.newdawn.slick.Input;

/**
 * The Class Vehicle.
 * 
 * @author Ziqi Jia
 * @Description: An abstract Entity that holds information and behaviours for a vehicle. The detailed implementation is
 *               done in subclasses.
 */
public abstract class Vehicle extends Sprite
{

    /** Flag to determine the moving direction. */
    private boolean moveRight;

    /** The speed of the vehicle. */
    private float speed;

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
     * Instantiates a new vehicle.
     *
     * @param imageSrc the image source of the vehicle.
     * @param x The x-coordinate of the vehicle.
     * @param y The y-coordinate of the vehicle.
     * @param moveRight the moving direction
     * @param hazard the flag to determine if the vehicle is hazard.
     */
    public Vehicle(String imageSrc, float x, float y, boolean moveRight, boolean hazard)
    {
        super(imageSrc, x, y, hazard);
        this.moveRight = moveRight;
    }

    /**
     * {@inheritDoc} Update the information regarding the movement of the vehicle.
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
     * {@inheritDoc} Detailed behaviour once the enemy is moving. Details vary depending on the enemy type.
     *
     * @see Sprite#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        move(speed * delta * (moveRight ? 1 : -1), 0);

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
