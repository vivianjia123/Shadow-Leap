import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

/**
 * The Class Sprite.
 *
 * @author Ziqi Jia
 * @Description: An abstract Entity that holds information and behaviours for a sprite. The detailed implementation is
 *               done in subclasses.
 */
public abstract class Sprite
{

    /** Flag to define if the sprite is hazard or not. */
    private boolean hazard;

    private boolean active = true;

    /** The BoundingBox for determining overlaps with other sprites. */
    private BoundingBox bb;

    /** The image of the sprite. */
    private Image image;

    /** The x-coordinate of the sprite. */
    private float x;

    /** The y-coordinate of the sprite. */
    private float y;

    /**
     * Instantiates a new sprite with image and position.
     *
     * @param imageSrc the image source of the sprite.
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     */
    public Sprite(String imageSrc, float x, float y)
    {
        setupSprite(imageSrc, x, y);
    }

    /**
     * Instantiates a new sprite with image, position and hazard.
     *
     * @param imageSrc the image source of the sprite.
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     * @param hazard the flag to determine if the sprite is hazard.
     */
    public Sprite(String imageSrc, float x, float y, boolean hazard)
    {
        setupSprite(imageSrc, x, y);
        this.hazard = hazard;
    }

    /**
     * Setup sprite with the position, image and bounding box. Should be called in constructor.
     *
     * @param imageSrc the image source of the sprite.
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     */
    private void setupSprite(String imageSrc, float x, float y)
    {
        try {
            image = new Image(imageSrc);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        bb = new BoundingBox(image, (int) x, (int) y);
    }

    /*
     * Makes sure the sprite will not go off screen, and will just stay at the edges.
     */
    public void clampToScreen()
    {
        y = Math.max(y, 0);
        y = Math.min(y, App.SCREEN_HEIGHT);
    }

    /**
     * Flag to determine whether sprite is still on screen.
     *
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     * @return true, if it is on screen
     */
    public final boolean onScreen(float x, float y)
    {
        return !(x + World.TILE_SIZE / 2 > App.SCREEN_WIDTH || x - World.TILE_SIZE / 2 < 0
            || y + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT || y - World.TILE_SIZE / 2 < 0);
    }

    /**
     * Flag to determine whether sprite is still on screen.
     *
     * @return true, if it is on screen
     */
    public final boolean onScreen()
    {
        return onScreen(getX(), getY());
    }

    /**
     * Flag to determine whether sprite is contacted with other sprite.
     *
     * @param other the other
     * @return true, if it is contacted with other sprite.
     */
    public final boolean collides(Sprite other)
    {
        return bb.intersects(other.bb);
    }

    /**
     * Determine the behavior of sprite if in contact with sprite, an empty method of contactSprite for further
     * implementation in subclasses.
     *
     * @param other the other object that contact with the sprite
     */
    public void onCollision(Sprite other)
    {
    }

    /**
     * Determine the position of the sprite, an empty method for further implementation in subclasses.
     */
    public void resetPosition()
    {
    }

    /**
     * Detailed behaviour of each sprite. Details vary depending on the sprite type.
     */
    public void spriteBehaviour()
    {
    }

    /**
     * Detailed behaviour of each sprite with variables. Details vary depending on the sprite type.
     *
     * @param delta The time that has elapsed.
     * @param speed the speed of the sprite.
     */
    public void spriteBehaviour(int delta, float speed)
    {
    }

    /**
     * Updates information, should be called every time something new occurs in game per frame.
     * 
     * @param input The Slick input object.
     * @param delta The time that has elapsed.
     */
    public void update(Input input, int delta)
    {
    }

    /**
     * Render the sprite from its center at the specified location.
     */
    public void render()
    {
        image.drawCentered(x, y);
    }

    /**
     * Sets the x position of the sprite.
     * 
     * @param x the target x position
     */
    public final void setX(float x)
    {
        this.x = x;

    }

    /**
     * Sets the y position of the sprite.
     * 
     * @param y the target y position
     */
    public final void setY(float y)
    {
        this.y = y;

    }

    /**
     * Accesses the x position of the sprite.
     * 
     * @return the x position of the sprite
     */
    public final float getX()
    {
        return x;
    }

    /**
     * Accesses the y position of the sprite.
     * 
     * @return the y position of the sprite
     */
    public final float getY()
    {
        return y;
    }

    /**
     * Gets the bb.
     *
     * @return the bb
     */
    public BoundingBox getBb()
    {
        return bb;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Sets the bb.
     *
     * @param bb the new bb
     */
    public void setBb(BoundingBox bb)
    {
        this.bb = bb;
    }

    /**
     * Checks if is hazard.
     *
     * @return true, if is hazard
     */
    public boolean isHazard()
    {
        return hazard;
    }

    /**
     * Sets the hazard.
     *
     * @param hazard the new hazard
     */
    public void setHazard(boolean hazard)
    {
        this.hazard = hazard;
    }

    /**
     * @return the active
     */
    public boolean getActive()
    {
        return active;
    }

    /**
     * Deactivates this extra life.
     */
    public void deactivate()
    {
        active = false;
    }

    /**
     * Move.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void move(float dx, float dy)
    {
        x += dx;
        y += dy;
        bb.setX(x);
        bb.setY(y);
    }

}
