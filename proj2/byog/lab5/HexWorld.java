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
    public static void addHexagon(int x, int y, int size, TETile[][] world, TETile tile) {
        addTrapezoid(x, y, size, size/2, -1, world, tile);
        addTrapezoid(x, y + 1, size, size/2, 1, world, tile);
    }

    public static void addTrapezoid(int startX, int startY, int bottomWidth, int height, int step, TETile[][] world, TETile tile) {
    //TETile is a class here
        for (int j = 0; j < height; j += 1) {
            if (bottomWidth - 2*j < 2){
                break;
            }
            for (int i = j; i < bottomWidth - j; i += 1) {
                world[i + startX][step*j + startY] = tile;
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

    /*move up a line and go to the right
    * good for drawing hexagons (with sides slanting up)*/
    public static int[] upALine(int xStart, int yStart, int sizeOfUnit){
        int topWidth = calcTopWidth(sizeOfUnit);
        int[] xy = {xStart + sizeOfUnit / 2 + topWidth - 1, yStart + sizeOfUnit / 2};
        return xy;
    }

    public static int[] downALine(int xStart, int yStart, int sizeOfUnit){
        int topWidth = calcTopWidth(sizeOfUnit);
        int[] xy = {xStart + sizeOfUnit / 2 + topWidth - 1, yStart - sizeOfUnit / 2};
        return xy;
    }

    public static int[] downALineLeft(int xStart, int yStart, int sizeOfUnit){
        int topWidth = calcTopWidth(sizeOfUnit);
        int[] xy = {xStart - sizeOfUnit / 2 - topWidth + 1, yStart - sizeOfUnit / 2};
        return xy;
    }

    public static void drawLargeHex(int startX, int startY, int sideLen, int unitWidth, TETile[][] world, TETile tile){
        int layers = sideLen;
        int yStep = 2*(1 + (unitWidth - calcTopWidth(unitWidth))/2);
        for (int i = 0; i < sideLen; i++){
            drawLargeL(startX, startY, sideLen, unitWidth, world, tile);
            startY -= yStep;
        }
        startY += yStep;
        for (int j = 1; j < sideLen; j++){
            int[] xy = downALine(startX, startY, unitWidth);
            startX = xy[0];
            startY = xy[1];
            drawLargeL(startX, startY, sideLen - j, unitWidth, world, tile);
        }


    }

    public static void drawLargeL(int startX, int startY, int sideLen, int unitWidth, TETile[][] world, TETile tile){
        int xPtr = startX;
        int yPtr = startY;
        for (int i = 0; i < sideLen; i++){
            addHexagon(xPtr, yPtr, unitWidth, world, tile);
            int[] xyNext = upALine(xPtr, yPtr, unitWidth);
            xPtr = xyNext[0];
            yPtr = xyNext[1];
        }

        int[] xyNext = downALineLeft(xPtr, yPtr, unitWidth);
        xPtr = xyNext[0];
        yPtr = xyNext[1];

        for (int i = 0; i < sideLen; i ++){
            addHexagon(xPtr, yPtr, unitWidth, world, tile);
            xyNext = downALine(xPtr, yPtr, unitWidth);
            xPtr = xyNext[0];
            yPtr = xyNext[1];
        }
    }
}
