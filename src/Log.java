
/**
 * The Class Log.
 *
 * @author Ziqi Jia
 * @Description: A rideable log that move in the water.
 */
public class Log extends RideableObject
{

    /** The image path of the log. */
    private static final String LOG_PATH = "assets/log.png";

    /** The Constant SPEED of the longlog. */
    private static final float SPEED = 0.1f;

    /**
     * Instantiates a new log.
     *
     * @param x The x-coordinate of the log.
     * @param y The y-coordinate of the log.
     * @param moveRight the moving direction
     */
    public Log(float x, float y, boolean moveRight)
    {
        super(LOG_PATH, x, y, moveRight);
    }

    /**
     * {@inheritDoc} The log's movement behaviour.
     * 
     * @see RideableObject#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
    }

}
