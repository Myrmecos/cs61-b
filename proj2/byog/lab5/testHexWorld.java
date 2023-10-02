package byog.lab5;

import byog.TileEngine.Tileset;
import org.junit.Test;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class testHexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 80;

    private static TERenderer initializeBackground(){
        TERenderer ter = new TERenderer(); //displayer
        ter.initialize(WIDTH, HEIGHT);
        return ter;
    }
    private static TETile[][] initializeWorld(){

        TETile[][] world = new TETile[WIDTH][HEIGHT]; //fill with tiles
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }
        return world;
    }
    @Test
    public void addTrepazoidTest(){
        TERenderer ter = new TERenderer(); //displayer
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT]; //fill with tiles
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }
        HexWorld.addTrapezoid(5, 20, 6, 3, 1, world, Tileset.WALL);
        ter.renderFrame(world);
    }

    @Test
    public void moveHexTest(){
        TERenderer ter = testHexWorld.initializeBackground();
        TETile[][] world = initializeWorld();
        int x1 = 5, y1 = 20, size = 8;
        HexWorld.addHexagon(x1, y1, size, world, Tileset.FLOWER);
        int x2, y2, x3, y3;
        int[] xy = HexWorld.moveToRight(x1, y1, size);
        x2 = xy[0];
        y2 = xy[1];
        int [] x3y3 = HexWorld.upALine(x1, y1, size);
        x3 = x3y3[0];
        y3 = x3y3[1];
        HexWorld.addHexagon(x2, y2, size, world, Tileset.WALL);
        HexWorld.addHexagon(x3, y3, size, world, Tileset.MOUNTAIN);
        ter.renderFrame(world);
    }
    @Test
    public void testDrawLargeL(String[] args){
        TERenderer ter = testHexWorld.initializeBackground();
        TETile[][] world = initializeWorld();
        int x1 = 5, y1 = 20, size = 8;
        TETile tile = Tileset.WALL;
        HexWorld.drawLargeL(x1, y1, 3, size, world, tile);
        ter.renderFrame(world);
    }


    public static void main(String[] args){
        TERenderer ter = testHexWorld.initializeBackground();
        TETile[][] world = initializeWorld();
        int x1 = 5, y1 = 60, size = 8, bottomWidth = 3;
        TETile tile = Tileset.FLOWER;
        HexWorld.drawLargeHex(x1, y1, bottomWidth, size, world, tile);
        ter.renderFrame(world);
    }

    @Test
    public void example() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        for (int x = 20; x < 35; x += 1) {
            for (int y = 5; y < 10; y += 1) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
// a random line out of nowhere