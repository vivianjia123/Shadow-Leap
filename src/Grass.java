
/**
 * The Class Grass.
 * 
 * @author Ziqi Jia
 * @Description: An Entity that represent the backgroud Grass tile.
 */
public class Grass extends Tile
{

    /** The image path of the grass. */
    private static final String GRASS_PATH = "assets/grass.png";

    /**
     * Instantiates a new grass.
     *
     * @param x The x-coordinate of the grass.
     * @param y The y-coordinate of the grass.
     */
    public Grass(float x, float y)
    {
        super(GRASS_PATH, x, y, false);
    }
}
