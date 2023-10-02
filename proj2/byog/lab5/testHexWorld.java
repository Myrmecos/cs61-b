package byog.lab5;

import byog.TileEngine.Tileset;
import org.junit.Test;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class testHexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 40;

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
        HexWorld.addTrapezoid(5, 20, 6, 3, 1, world);
        ter.renderFrame(world);
    }


    public static void main(String[] args){
        TERenderer ter = testHexWorld.initializeBackground();
        TETile[][] world = initializeWorld();
        HexWorld.addHexagon(5, 20, 10, world);
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
