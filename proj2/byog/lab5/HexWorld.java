package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(int x, int y, int size, TETile[][] world) {
        addTrapezoid(x, y, size, size/2, -1, world);
        addTrapezoid(x, y + 1, size, size/2, 1, world);
    }

    public static void addTrapezoid(int startX, int startY, int bottomWidth, int height, int step, TETile[][] world) {

        for (int j = 0; j < height; j += 1) {
            for (int i = j; i < bottomWidth - j; i += 1) {
                world[i + startX][step*j + startY] = Tileset.WALL;
            }
        }
    }
}
