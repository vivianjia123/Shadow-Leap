
/**
 * The Class LongLog.
 *
 * @author Ziqi Jia
 * @Description: A rideable longlog that move in the water.
 */
public class LongLog extends RideableObject
{

    /** The image path of the longlog. */
    private static final String LONGLOG_PATH = "assets/longlog.png";

    /** The Constant SPEED of the longlog. */
    private static final float SPEED = 0.07f;

    /**
     * Instantiates a new long log.
     *
     * @param x The x-coordinate of the longlog.
     * @param y The y-coordinate of the longlog.
     * @param moveRight the moving direction
     */
    public LongLog(float x, float y, boolean moveRight)
    {
        super(LONGLOG_PATH, x, y, moveRight);
    }

    /**
     * {@inheritDoc} The longlog's movement behaviour
     * 
     * @see RideableObject#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
    }
}
