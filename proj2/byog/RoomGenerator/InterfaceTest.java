package byog.RoomGenerator;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class InterfaceTest {

    public static void initializeWorld(){}
    public static void runGame(){
        Interface itf = new Interface();
        itf.initializeWorld(40, 15, 5);

        TERenderer ter = new TERenderer();
        ter.initialize(itf.mz.width, itf.mz.height);
        ter.renderFrame(itf.mz.world);
    }
    public static void main(String[] args){
        /*TETile[][] world = new TETile[81][31];
        mazeGenerator mz = new mazeGenerator(5);
        int[][] background = mz.initBackground(world);
        mz.makeMaze();
        mz.mapToWorld();
        RoomGenerator rg = new RoomGenerator(world, 5);
        RoomGenerator.Room[] rmList1 = rg.makeRoomList(40);
        rg.DrawRoomList(rmList1);
        rg.generateDoor(rmList1, mz.world);




        TERenderer ter = new TERenderer();
        ter.initialize(mz.width, mz.height);
        ter.renderFrame(mz.world);*/

        Interface itf = new Interface();
        itf.makeInterface();
        //itf.runGame();
        System.out.println("end");
    }
}
