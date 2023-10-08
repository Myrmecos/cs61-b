package byog.RoomGenerator;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static byog.RoomGenerator.mazeGenerator.printMatrix;

public class mazeGeneratorTest {

    TETile[][] world = new TETile[31][31];
    mazeGenerator mz = new mazeGenerator();
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
        mazeGenerator mz = new mazeGenerator();
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
        TETile[][] world = new TETile[31][31];
        mazeGenerator mz = new mazeGenerator();
        int[][] background = mz.initBackground(world);
        mz.makeMaze();
        mz.mapToWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(mz.width, mz.height);
        ter.renderFrame(mz.world);
    }
}
