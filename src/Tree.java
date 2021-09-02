
/**
 * The Class tree.
 * 
 * @author Ziqi Jia
 * @Description: An Entity that represent the backgroud tree tile.
 */
public class Tree extends Tile
{

    /** The image path of the tree. */
    private static final String TREE_PATH = "assets/tree.png";

    /**
     * Instantiates a new tree.
     *
     * @param x The x-coordinate of the tree.
     * @param y The y-coordinate of the tree.
     */
    public Tree(float x, float y)
    {
        super(TREE_PATH, x, y, false);

    }

    /**
     * {@inheritDoc} The behaviour when the tree make contact with player, The player should not be able to move into
     * tree tiles.
     * 
     * @see Sprite#onCollision(Sprite)
     */
    @Override
    public void onCollision(Sprite other)
    {
        if (other instanceof Player) {
            other.setX(other.getX() - ((Player) other).getxPos());
            other.setY(other.getY() - ((Player) other).getyPos());
            ((Player) other).setxPos(0);
            ((Player) other).setyPos(0);

        }

    }

}
