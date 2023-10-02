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
            if (bottomWidth - 2*j < 2){
                break;
            }
            for (int i = j; i < bottomWidth - j; i += 1) {
                world[i + startX][step*j + startY] = Tileset.WALL;
            }
        }
    }

    /* draw a large hexagon*/
    public static void largeHexagon(){
        int a = 1;
    }

    /*calculate the width of hexagon top */
    public static int calcTopWidth(int sizeOfUnit){
        int topWidth;
        if (sizeOfUnit % 2 == 1){
            topWidth = 3;
        } else {
            topWidth = 2;
        }
        return topWidth;
    }

    /*move to adjacent hexagon's starting edge
    * size of unit is the largest width of the small hexagons*/
    public static int[] moveToRight(int xStart, int yStart, int sizeOfUnit){
        /* int topWidth;
        if (sizeOfUnit % 2 == 1){
            topWidth = 3;
        } else {
            topWidth = 2;
        }*/
        int topWidth = calcTopWidth(sizeOfUnit);
        int[] xy = {xStart + sizeOfUnit + topWidth, yStart};
        return xy;
    }

    public static int[] upALine(int xStart, int yStart, int sizeOfUnit){
        int topWidth = calcTopWidth(sizeOfUnit);
        int[] xy = {xStart + sizeOfUnit / 2 + topWidth - 1, yStart + sizeOfUnit / 2};
        return xy;
    }
}
