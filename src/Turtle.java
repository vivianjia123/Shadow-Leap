
/**
 * The Class Turtle.
 *
 * @author Ziqi Jia
 * @Description: A rideable turtle that move above and under the water.
 */
public class Turtle extends RideableObject
{

    /** The image path of turtle. */
    private static final String TURTLES_PATH = "assets/turtles.png";

    /** The Constant SPEED of turtle. */
    private static final float SPEED = 0.085f;

    /** The appear time that turtle is above water. */
    private static final float APPEAR_TIME = 7000;

    /** The disappear time that turtlr is under water. */
    private static final float DISAPPEAR_TIME = 2000;

    /** The timer represents the initial time when turtle appear. */
    private int timer = 0;

    /** Flag to determine if the turtle appear. */
    private boolean isAppear = false;

    /**
     * Instantiates a new turtle.
     *
     * @param x The x-coordinate of the turtle.
     * @param y The y-coordinate of the turtle.
     * @param moveRight the moving direction
     */
    public Turtle(float x, float y, boolean moveRight)
    {
        super(TURTLES_PATH, x, y, moveRight);
    }

    /**
     * {@inheritDoc} The behaviour of turtle as it appear above the water for 7 seconds and disappear underwater for 2
     * seconds.
     * 
     * @see RideableObject#spriteBehaviour(int, float)
     */
    @Override
    public void spriteBehaviour(int delta, float speed)
    {
        super.spriteBehaviour(delta, SPEED);
        if (World.getTimer() - timer <= APPEAR_TIME) {
            isAppear = true;
        }
        if (World.getTimer() - timer > APPEAR_TIME && World.getTimer() - timer < DISAPPEAR_TIME + APPEAR_TIME) {
            isAppear = false;
        }
        if (World.getTimer() - timer >= DISAPPEAR_TIME + APPEAR_TIME) {
            timer = World.getTimer();
        }

    }

    /**
     * {@inheritDoc} Render the turtle if it is above the water.
     * 
     * @see Sprite#render()
     */
    @Override
    public void render()
    {
        if (isAppear) {
            super.render();
        }
    }

    /**
     * {@inheritDoc} The behaviour when turtle make contact with the player.
     * 
     * @see RideableObject#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        if (other instanceof Player) {
            // The player should only in contact with the turtle when it appear above the water.
            if (isAppear == true) {
                super.onCollision(other);
            }
        }
    }
}
