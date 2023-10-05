package byog.RoomGenerator;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.lang.Math;
import java.util.Random;

import static byog.RoomGenerator.Room.generateRandomRoom;
import static byog.RoomGenerator.Room.plotAllRooms;


public class TestRoom {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    TERenderer ter = new TERenderer();
    TETile[][] world = new TETile[WIDTH][HEIGHT];
    @Test
    public void TestMakeRoom() {
        Room firstRoom = new Room(15, 10, 5, 5, world, 1);
        firstRoom.printRoom();
    }

    public static TETile[][] setZero(TETile[][] world){
        int worldWidth = world[0].length;
        int worldHeight = world.length;
        for (int i = 0; i < worldWidth; i++){
            for (int j = 0; j < worldHeight; j++){
                world[j][i] = Tileset.NOTHING;
            }
        }
        return world;
    }

    @Test
    public void checkOverlapTest(){
        Room room1 = new Room(10, 10, 0, 0, world, 1);
        Room room2 = new Room(10, 10, 0, 0, world, 2);
        room1.xPos = 10;
        room1.yPos = 10;
        room2.xPos = 15;
        room2.yPos = 20;
        room1.printRoom();
        room2.printRoom();
        System.out.println(room1.checkOverlap(room2));
    }

    @Test
    public void checkAllOverlapTest(){
        Room room1 = new Room(10, 10, 0, 0, world, 1);
        Room room2 = new Room(10, 10, 0, 0, world, 2);
        room1.xPos = 10;
        room1.yPos = 10;
        room2.xPos = 15;
        room2.yPos = 21;
        Room[] arr = new Room[]{room1, room2};

        Room room3 = new Room(10, 10, 0, 0, world, 1);
        room3.xPos = 26;
        room3.yPos = 32;
        boolean tf = room1.checkAllOverlap(arr);
        System.out.println(room3.checkOverlap(room1));
        System.out.println(room3.checkOverlap(room2));
        System.out.println(tf);
    }

    @Test
    public void plotAllRoomsTest(){
        Room room1 = new Room(10, 10, 0, 0, world, 1);
        Room room2 = new Room(10, 10, 0, 0, world, 2);
        room1.xPos = 10;
        room1.yPos = 10;
        room2.xPos = 15;
        room2.yPos = 21;
        Room[] arr = new Room[]{room1, room2};
        plotAllRooms(arr, world);
    }

    @Test
    public void checkOutOfBoundTest(){
        Room room1 = new Room(10, 10, 0, 0, world, 1);
        Room room2 = new Room(13, 7, 0, 0, world, 2);
        room1.xPos = 10;
        room1.yPos = 10;
        room2.xPos = 23;
        room2.yPos = 51;
        System.out.println(Room.checkOutOfBound(room2, world));
        //System.out.println(Room.checkOutOfBound(room1, world));
    }
    @Test
    public void generateRandomRoomTest(){}


    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH,HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        TETile[][] world1 = setZero(world);

        //===============================================
        /*
        Room firstRoom = new Room(5, 8, 2, 2, world);
        firstRoom.drawRoom(world);
        firstRoom.printRoom();
        ter.renderFrame(world1);*/

        //=======================PlotAllRoomTest==========

        Room room1 = new Room(10, 10, 0, 0, world, 1);
        Room room2 = new Room(10, 10, 0, 0, world, 2);
        room1.xPos = 10;
        room1.yPos = 10;
        room2.xPos = 15;
        room2.yPos = 21;
        Room[] arr = new Room[]{room1, room2};
        /*for (Room i : arr){
            i.drawRoom(world1);
        }*/
        Room[] arrRand = generateRandomRoom(16, world1);

        Room.plotAllRooms(arrRand, world1);
















        ter.renderFrame(world1); //NEVER COMMENT OUT THIS LINE
    }

}
