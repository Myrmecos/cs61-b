package byog.RoomGenerator;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

import static java.lang.Math.max;

public class RoomGenerator {
    TETile[][] world;
    Random rdn;
    int WIDTH;
    int HEIGHT;
    public static class Room{
        int xPos;
        int yPos;
        int width;
        int height;
        public Room(int x, int y, int w, int h){
            xPos = x;
            yPos = y;
            width = w;
            height = h;
        }
    }
    public RoomGenerator(TETile[][] world, int seed){
        this.world = world;
        rdn = new Random(seed);
        WIDTH = world.length;
        HEIGHT = world[0].length;
    }
    public Room randomRoom(int width, int height, int varX, int varY){
        int widthR = width + rdn.nextInt(max(varX, 1));
        int heightR = height + rdn.nextInt(max(varY, 1));
        int xPos = rdn.nextInt(WIDTH - widthR);
        int yPos = rdn.nextInt(HEIGHT - heightR);
        Room ret = new Room(xPos, yPos, widthR, heightR);
        return ret;
    }

    public void drawRoom(Room rm){
        for (int i = 0; i < rm.width; i ++){
            for (int j = 0; j < rm.height; j ++){
                world[i + rm.xPos][j + rm.yPos] = Tileset.FLOOR;
            }
        }
    }

    public static boolean checkOverlap(Room r1, Room r2){
        int x1 = r1.xPos;
        int x2 = r2.xPos;
        int y1 = r1.yPos;
        int y2 = r2.yPos;
        int w1 = r1.width;
        int w2 = r2.width;
        int h1 = r1.height;
        int h2 = r2.height;

        if ((x1 + w1 < x2 | x2 + w2 < x1) & (y1 + h1 < y2 | y2 + h2 < y1)){
            return false;
        }
        return true;
    }

    public Room[] makeRoomList(){
        return null;
    }

    public void DrawRoomList(Room[] roomList){
        for (Room i: roomList){
            if (i != null) {
                drawRoom(i);
            }
        }
    }


}
