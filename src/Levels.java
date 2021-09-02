import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The Class Levels.
 * 
 * @author Ziqi Jia
 * @Description: Class used just for creating sprite purposes
 */
public class Levels
{

    /**
     * Reads a level file and create sprites from it, should be in format (Name,x,y,direction).
     *
     * @param levelFile path to the file to be read
     */
    public static void readLevelFormat(String levelFile)
    {
        World tempWorld = World.getInstance();

        try (BufferedReader br = new BufferedReader(new FileReader(levelFile))) {
            String line = null;
            while ((line = br.readLine()) != null) {

                // read the data from the line
                String[] data = line.split(",");

                String className = data[0];
                className = className.substring(0, 1).toUpperCase() + className.substring(1);
                int x = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);

                Class<?> c = Class.forName(className);

                // determine if the sprite has a direction.
                if (data.length == 4) {
                    boolean moveRight = Boolean.parseBoolean(data[3]);
                    tempWorld.addSprite((Sprite) c.getConstructor(new Class[] {float.class, float.class, boolean.class})
                        .newInstance(new Object[] {x, y, moveRight}));
                    // add all log and longlog to the randomLog arraylist, so the extralife object can be created on a
                    // random log from this arraylist.
                    if (className.equals("Log") || className.equals("LongLog")) {
                        tempWorld.addLog(
                            (RideableObject) c.getConstructor(new Class[] {float.class, float.class, boolean.class})
                                .newInstance(new Object[] {x, y, moveRight}));
                    }
                } else {
                    tempWorld.addSprite((Sprite) c.getConstructor(new Class[] {float.class, float.class})
                        .newInstance(new Object[] {x, y}));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // create black tiles
        for (int i = (int) (World.TILE_SIZE * 2.5); i < App.SCREEN_WIDTH; i += World.TILE_SIZE * 4) {
            tempWorld.addSprite(new BlackTile(Player.getPlayerPath(), i, World.TILE_SIZE));
        }

    }

}
