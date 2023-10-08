package byog.RoomGenerator;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;
import byog.RoomGenerator.RoomGenerator;

import java.util.Random;

import static byog.RoomGenerator.mazeGenerator.printMatrix;

public class mazeGeneratorTest {

    TETile[][] world = new TETile[31][31];
    mazeGenerator mz = new mazeGenerator(5);
    int[][] background = mz.initBackground(world);

    @Test
    public void testPrintMatrix(){
        int[][] mat = new int[2][2];
        mat[0][0] = 1;
        mat[0][1] = 2;
        mat[1][0] = 3;
        mat[1][1] = 4;
        printMatrix(mat);
    }

    @Test
    public void testInitBackground(){
        mazeGenerator mz = new mazeGenerator(5);
        int[][] background = mz.initBackground(world);
        printMatrix(background);
    }

    @Test
    public void testBreakWall(){
        mz.breakWall(new Integer[]{1,1}, new Integer[] {1, 3});
        printMatrix(background);
    }

    @Test
    public void testCheckSurrounding() {
        Integer[] currentPos = new Integer[] {3, 3};
        mz.maze[1][3] = 2;
        printMatrix(background);
        System.out.println(mz.canGo(new Integer[] {1, 3}));
        Integer[][] possibleDir = mz.checkSurrounding(currentPos);
        mazeGenerator.printIntMatrix(possibleDir);

    }

    @Test
    public void testRandomize() {
        Integer[] currentPos = new Integer[] {3, 3};
        printMatrix(background);
        System.out.println(mz.canGo(currentPos));
        Integer[][] possibleDir = mz.checkSurrounding(currentPos);
        Integer[][] possibleDirRand = mz.randomize(possibleDir);
        mazeGenerator.printIntMatrix(possibleDirRand);
    }

    @Test
    public void testMakeMaze(){
        //mz.maze[1][3] = 2;
        mz.makeMaze();

        printMatrix(background);

    }

    public static void main(String[] args){
        TETile[][] world = new TETile[81][31];
        mazeGenerator mz = new mazeGenerator(5);
        mz.rdn = new Random(5);
        int[][] background = mz.initBackground(world);
        mz.makeMaze();
        mz.mapToWorld();
        RoomGenerator rg = new RoomGenerator(world, 5);
        RoomGenerator.Room rm = rg.randomRoom(16, 14, 0, 0);
        rg.drawRoom(rm);
        RoomGenerator.Room r1 = new RoomGenerator.Room(1, 1, 10, 10);
        RoomGenerator.Room r2 = new RoomGenerator.Room(1, 1, 10, 10);
        System.out.println(RoomGenerator.checkOverlap(r1, r2));







        TERenderer ter = new TERenderer();
        ter.initialize(mz.width, mz.height);
        ter.renderFrame(mz.world);
    }
}
