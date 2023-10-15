package byog.RoomGenerator;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
public class Interface {
    TETile[][] world;
    public mazeGenerator mz;
    public RoomGenerator rg;
    private int frmWidth = 40; //for setting width of the opening frame before game
    private int frmHeight = 40;
    private int charX;
    private int charY;
    TERenderer ter;
    Random rand = new Random(100);
    private boolean refreshFlag = false;

    public void drawChar() {
        world[charX][charY] = Tileset.PLAYER;
    }

    public void eraseChar() {
        world[charX][charY] = Tileset.FLOOR;
    }
    public void makeFrame(){
        StdDraw.setCanvasSize(frmWidth * 16, frmHeight * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, frmWidth);
        StdDraw.setYscale(0, frmHeight);
        //StdDraw.clear(Color.black);
        StdDraw.enableDoubleBuffering();
    }

    public void makeInterface(){
        makeFrame();
        StdDraw.clear(StdDraw.BLACK);
        boolean status = true;
        while (status){
            if (! refreshFlag){
                StdDraw.clear(StdDraw.BLACK);
                drawText();
                StdDraw.pause(1000);
                StdDraw.clear(StdDraw.BLACK);
            }
            if (StdDraw.hasNextKeyTyped()|refreshFlag){
                char key = 'o';
                if (! refreshFlag){key = StdDraw.nextKeyTyped();}
                if (refreshFlag) {
                    refreshFlag = false;
                    //refresh
                    key = 'n';
                }

                if (key == 'q') {
                    status = false;
                }
                if (key == 'l' & world != null) {
                    loadOldGame();
                    drawChar();
                    ter.renderFrame(mz.world);
                    boolean playGame = true;
                    while (playGame) {
                        playGame = drawNextStep();
                        StdDraw.pause(100);
                        ter.renderFrame(mz.world);
                        if (refreshFlag) {break;}
                    }
                    makeFrame();
                }

                if (key == 'n') {
                    StdDraw.enableDoubleBuffering();
                    runGame();
                    drawChar();
                    ter.renderFrame(mz.world);
                    boolean playGame = true;
                    while (playGame) {
                        playGame = drawNextStep();
                        StdDraw.pause(100);
                        ter.renderFrame(mz.world);
                        if (refreshFlag) {break;}
                        }
                    makeFrame();
                }
            }
        }
        System.out.println("end of game");
    }

    public boolean drawNextStep() {
        boolean ret = true;

        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (key == 'h') {
                if (canGo(charX - 1, charY)) {
                    eraseChar();
                    charX -= 1;
                }
            }
            if (key == 'j') {
                if (canGo(charX, charY - 1)) {
                    eraseChar();
                    charY -= 1;
                }
            }
            if (key == 'k') {
                if (canGo(charX, charY + 1)) {
                    eraseChar();
                    charY += 1;
                }
            }
            if (key == 'l') {
                if (canGo(charX + 1, charY)) {
                    eraseChar();
                    charX += 1;
                }
            }
            drawChar();
            return key != 'q';
        }
        return true;
    }

    public boolean canGo(int x, int y){
        int wth = world.length;
        int hgh = world[0].length;
        if (x < wth & x > 0 & y > 0 & y < hgh) {
            if (world[x][y] == Tileset.LOCKED_DOOR){
                refreshFlag = true;
            }
            return world[x][y] != Tileset.WALL;
        }
        return false;
    }

    public void drawFrame(String str, int x, int y, int fontSize){
        Font font = new Font("Monaco", Font.BOLD, fontSize);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y, str);
        StdDraw.show();
    }

    public void drawText() {
        drawFrame("CS61B Game", frmWidth/2, frmHeight/2 + 8, 30);
        drawFrame("New Game (N)", frmWidth/2, frmHeight/2 + 2, 20);
        drawFrame("Load Game (L)", frmWidth/2, frmHeight/2, 20);
        drawFrame("Quit (Q)", frmWidth/2, frmHeight/2 - 2, 20);
    }





    public void loadOldGame() {
        ter = new TERenderer();
        ter.initialize(mz.width, mz.height);
        ter.renderFrame(mz.world);
    }

    public void initializeWorld (int xSize, int ySize, int seed) {
        world = new TETile[2 * xSize + 1][2 * ySize + 1];
        mz = new mazeGenerator(seed);
        int[][] background = mz.initBackground(world);
        mz.makeMaze();
        mz.mapToWorld();
        rg = new RoomGenerator(world, seed);
        RoomGenerator.Room[] rmList1 = rg.makeRoomList(40);
        rg.DrawRoomList(rmList1);
        rg.generateDoor(rmList1, mz.world);
        charX = rmList1[0].xPos;
        charY = rmList1[0].yPos;
    }

    public void runGame(){
        initializeWorld(40, 15, rand.nextInt());

        ter = new TERenderer();
        ter.initialize(mz.width, mz.height);
        ter.renderFrame(mz.world);
    }



}
