
/**
 * The Class Tile.
 * 
 * @author Ziqi Jia
 * @Description: An abstract Entity that holds information and behaviours for a tile. The detailed implementation is
 *               done in subclasses.
 */
public abstract class Tile extends Sprite
{

    /**
     * Instantiates a new tile.
     *
     * @param imageSrc the image source of the tile.
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @param hazard the flag to determine if the sprite is hazard.
     */
    public Tile(String imageSrc, float x, float y, boolean hazard)
    {
        super(imageSrc, x, y, hazard);
    }
}
