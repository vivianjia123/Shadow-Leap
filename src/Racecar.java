
/**
 * The Class Racecar.
 * 
 * @author Ziqi Jia
 * @Description: The entity that represent a kind of vehicle bike.
 */
public class Racecar extends Vehicle
{

    /** The image path of racecar. */
    private static final String RACECAR_PATH = "assets/racecar.png";

    /** The Constant SPEED of racecar. */
    private static final float SPEED = 0.5f;

    /**
     * Instantiates a new racecar.
     *
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     * @param moveRight the moving direction
     */
    public Racecar(float x, float y, boolean moveRight)
    {
        super(RACECAR_PATH, x, y, moveRight, true);
    }

    /**
     * {@inheritDoc} The racecar's movement behaviour.
     * 
     * @see Vehicle#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
    }
}
