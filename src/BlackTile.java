import org.newdawn.slick.Input;

/**
 * The Class BlackTile.
 * 
 * @author Ziqi Jia
 * @Description: An entity represent the target tile of the player, as the player should reach to all blacktile to win
 *               the game.
 */
public class BlackTile extends Tile
{

    /** Flag to check if it is collided with the player. */
    private boolean isCollides = false;

    /** Flag to check if the tile is filled with the frog. */
    private boolean isFill = false;

    /** The number of tile. */
    private static int numberOfTile = 0;

    /** The number of level. */
    private static int numberOfLevel = 0;

    /**
     * Instantiates a new black tile.
     *
     * @param imageSrc the image source of the black tile.
     * @param x The x-coordinate of the sprite.
     * @param y The y-coordinate of the sprite.
     */
    public BlackTile(String imageSrc, float x, float y)
    {
        super(imageSrc, x, y, false);

    }

    /**
     * {@inheritDoc} Update the level if all tile has been filled with frog.
     * 
     * @see Sprite#update(org.newdawn.slick.Input, int)
     */
    @Override
    public void update(Input input, int delta)
    {
        levelChange();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Sprite#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        // if the black tile contact with the player, the player's position should be reset.
        if (other instanceof Player) {
            spriteBehaviour();
            ((Player) other).resetPosition();
        }

    }

    /**
     * {@inheritDoc} The blacktile's behaviour.
     * 
     * @see Sprite#spriteBehaviour()
     */
    @Override
    public void spriteBehaviour()
    {

        if (isFill) {
            // if the blacktile is filled with the frog, the player will lost a life.
            World.getInstance().lifeLost();
        } else {
            // if the blacktile is empty, the player will get contact with the black tile.
            isCollides = true;
            numberOfTile++;
        }
    }

    /**
     * {@inheritDoc} Render the BlackTile. if the tile get contact with the player, a frog will be drawed in the
     * position.
     * 
     * @see Sprite#render()
     */
    @Override
    public void render()
    {
        if (isCollides) {
            getImage().drawCentered(getX(), getY());
            isFill = true;
        }
    }

    /**
     * Change the game level.
     */
    private void levelChange()
    {
        if (numberOfTile == 5) {
            // If all the blacktiles are filled with frog, the game will change to level 1.
            numberOfLevel++;
            World.getInstance().changeLevel();
            numberOfTile = 0;
            if (numberOfLevel == 2) {
                // If the level 1 is finished, the game over.
                System.exit(0);
            }
        }
    }

}
