
/**
 * The Class Bike.
 * 
 * @author Ziqi Jia
 * @Description: The entity that represent a kind of vehicle bike.
 */
public class Bike extends Vehicle
{

    /** The image path of bike. */
    private static final String BIKE_PATH = "assets/bike.png";

    /** The Constant SPEED of bike. */
    private static final float SPEED = 0.2f;

    /** The Constant x coordinate to reverse direction. */
    private static final float X_REVERSE_ONE = 24;

    /** The Constant x coordinate to reverse direction. */
    private static final float X_REVERSE_TWO = 1000;

    /**
     * Instantiates a new bike.
     *
     * @param x The x-coordinate of the bike.
     * @param y The y-coordinate of the bike.
     * @param moveRight the moving direction
     */
    public Bike(float x, float y, boolean moveRight)
    {
        super(BIKE_PATH, x, y, moveRight, true);
    }

    /**
     * {@inheritDoc} The bike reverse direction when reaches an x coordinate of either 24 or 1000.
     * 
     * @see Vehicle#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        if (getX() > X_REVERSE_TWO || (getX() < X_REVERSE_ONE)) {
            setMoveRight(!isMoveRight());
        }
        super.spriteBehaviour(delta, SPEED);
    }

}
