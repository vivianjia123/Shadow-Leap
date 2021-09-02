
/**
 * The Class Bulldozer.
 * 
 * @author Ziqi Jia
 * @Description: The entity that represent a kind of vehicle bulldozer.
 */
public class Bulldozer extends Vehicle
{

    /** The image path of bulldozer. */
    private static final String BULLDOZER_PATH = "assets/bulldozer.png";

    /** The Constant SPEED of bulldozer. */
    private static final float SPEED = 0.05f;

    /**
     * Instantiates a new bulldozer.
     *
     * @param x The x-coordinate of the bulldozer.
     * @param y The y-coordinate of the bulldozer.
     * @param moveRight the moving direction
     */
    public Bulldozer(float x, float y, boolean moveRight)
    {
        super(BULLDOZER_PATH, x, y, moveRight, false);
    }

    /**
     * {@inheritDoc} The bulldozer's movement.
     * 
     * @see Vehicle#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
    }

    /**
     * {@inheritDoc} If the bulldozer contact with other sprites.
     * 
     * @see Sprite#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        if (other instanceof Player) {
            // the bulldozer push the player in its direction of travel.
            other.move((other.getX() - getX()) * SPEED, 0);
            if (other.getX() >= getX()) {
                other.setX(getX() + World.TILE_SIZE);
            } else {
                other.setX(getX() - World.TILE_SIZE);
            }

        }
    }

}
