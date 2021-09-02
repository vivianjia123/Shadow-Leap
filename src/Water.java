
/**
 * The Class water.
 * 
 * @author Ziqi Jia
 * @Description: An Entity that represent the backgroud water tile.
 */
public class Water extends Tile
{

    /** The image path of the water. */
    private static final String WATER_PATH = "assets/water.png";

    /**
     * Instantiates a new water.
     *
     * @param x The x-coordinate of the water.
     * @param y The y-coordinate of the water.
     */
    public Water(float x, float y)
    {
        // The hazard flag is true since the player will lose life if make contact with water.
        super(WATER_PATH, x, y, true);
    }
}
