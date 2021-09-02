
/**
 * The Class Bus.
 * 
 * @author Ziqi Jia
 * @Description: The entity that represent a kind of vehicle bike.
 */
public class Bus extends Vehicle
{

    /** The image path of bus. */
    private static final String BUS_PATH = "assets/bus.png";

    /** The Constant SPEED of bus. */
    private static final float SPEED = 0.15f;

    /**
     * Instantiates a new bus.
     *
     * @param x The x-coordinate of the bus.
     * @param y The y-coordinate of the bus.
     * @param moveRight the moving direction
     */
    public Bus(float x, float y, boolean moveRight)
    {
        super(BUS_PATH, x, y, moveRight, true);

    }

    /**
     * {@inheritDoc} The bus' movement behaviour.
     * 
     * @see Vehicle#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
    }

}
