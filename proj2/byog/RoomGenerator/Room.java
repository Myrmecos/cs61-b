package byog.RoomGenerator;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.lang.Math;
import java.util.Random;

public class Room {
    public int width;
    public int height;
    public int xPos; //x coordinate of the lower-left corner
    public int yPos; //y coordinate of the lower-left corner
    public int worldWidth;
    public int worldHeight;
    public Random rdn;
    public int seed = 1002;



    //generate a random room, var is the variation in size
    //width is the span of the room, wall included
    public Room(int width, int height, int wvar, int hvar, TETile[][] world, int seed){
        this.worldHeight = world[0].length;
        this.worldWidth = world.length;
        rdn = new Random(seed);
        double randW = rdn.nextDouble() * 2 - 1;
        double randH = rdn.nextDouble() * 2 - 1;
        double randX = rdn.nextDouble();
        double randY = rdn.nextDouble();
        this.width = width + (int) Math.round(randW * wvar);
        this.height = height + (int) Math.round(randH * hvar);
        this.xPos = (int) Math.round(randX * (worldWidth - width));
        this.yPos = (int) Math.round(randY * (worldHeight - height));
        this.seed = rdn.nextInt();
    }

    public void drawRoom(TETile[][] world){
        for (int i = 0; i < width; i++){
            int x = xPos + i;
            int y1 = yPos;
            int y2 = yPos + height -1;
            world[x][y1] = Tileset.WALL;
            world[x][y2] = Tileset.WALL;
        }
        for (int j = 0; j < height; j++){
            int x1 = xPos;
            int x2 = xPos + width -1;
            int y = yPos + j;
            world[x1][y] = Tileset.WALL;
            world[x2][y] = Tileset.WALL;
        }
        for (int k = 0; k < width - 2; k++){
            for (int l = 0; l < height - 2; l++){
                int x = xPos + 1 + k;
                int y = yPos + 1 + l;
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    public void printRoom(){
        String output = java.lang.String.format("width: %d; height: %d; position: (%d, %d) ", width, height, xPos, yPos);
        System.out.println(output);
    }

    //===========stage one completed==================
    //roomList has 32 spaces. That's probably enough.
    public boolean checkAllOverlap(Room[] roomList){
        for (Room i : roomList){
            if (i == null){
                int a = 0;
            }else {
                if (checkOverlap(i)) {
                    return true;
                }
            }
        }
        return false;
     }

     public boolean checkOverlap(Room i){
        boolean xOverlap = true;
        boolean yOverlap = true;
        int xRelPos21 = xPos + width - i.xPos;
        int xRelPos12 = xPos - i.xPos - i.width;

        if (xRelPos12 > 0 || xRelPos21 < 0){

            return false;
        }

        int yRelPos21 = yPos - i.yPos + height;
        int yRelPos12 = yPos - i.height - i.yPos;
        if (yRelPos12 > 0 || yRelPos21 < 0){
            return false;
        }

         return true;

     }

     public static boolean checkOutOfBound(Room i, TETile[][] world){
        int ylim = world[0].length;
        int xlim = world.length;

        if (i.xPos > 0 & i.xPos + i.width < xlim & i.yPos > 0 & i.yPos + i.height < ylim){
            return false;
        }
        return true;
     }


     public static void plotAllRooms(Room[] roomList, TETile[][] world){
        for (Room i : roomList){
            if (i != null) {
                i.drawRoom(world);
            }
        }
     }

     public static Room[] generateRandomRoom(int num, TETile[][] world){
        Random rdn = new Random(42);
        Room[] roomList = new Room[num];
        int cnt = 0;
        for (int i = 0; i < num; i++){
            Room newRoom = new Room(8, 10, 5, 5, world, rdn.nextInt());
            newRoom.printRoom();
            if (newRoom.checkAllOverlap(roomList) == false & checkOutOfBound(newRoom, world) == false){
                roomList[i] = newRoom;

            } else {
                cnt += 1;
                i--;
            }
            if (cnt >=90){
                break;
            }
        }
        return roomList;
     }


}
