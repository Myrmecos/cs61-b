package byog.RoomGenerator;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class mazeGenerator {
    TETile[][] world;
    int width;
    int height;
    int[][] maze;
    ArrayRingBuffer<Integer[]> queue;
    Random rdn = new Random(1000);
    /*takes a world and initialize background in number (grid)
    * then returns background*/
    public int[][] initBackground(TETile[][] world){
        this.world = world;
        width = world.length;
        height = world[0].length;
        maze = new int[width][height];
        queue = new ArrayRingBuffer<Integer[]>(width * height);
        queue.enqueue(new Integer[] {1, 1});

        if (width % 2 != 1 | height % 2 != 1){
            return null;
        }
        for (int i = 0; i < width; i += 2) {
            for (int j = 0; j < height; j ++) {
                maze[i][j] = 1;
            }
        }
        for (int i = 1; i < width; i+= 2){
            for (int j = 0; j < height; j += 2){
                maze[i][j] = 1;
            }
        }
        for (int i = 1; i < width; i += 2){
            for (int j = 1; j < height; j += 2){
                maze[i][j] = 0;
            }
        }
        return maze;
    }

    /*takes a matrix, print each row*/

    public static void printMatrix (int[][] mat){
        for (int[] i: mat){
            for (int j: i){
                System.out.print(j + ", ");
            }
            System.out.println();
        }
    }

    public static void printIntMatrix (Integer[][] mat){
        for (Integer[] i: mat){
            for (Integer j: i){
                System.out.print(j + ", ");
            }
            System.out.println();
        }
    }

    /*make routes in maze*/
    public void makeMaze() {
        while (queue.peek() != null) {

            Integer[] currentPos = queue.dequeue();
            /*if (!canMark(currentPos)){
                continue;
            }*/
            markCurrentPos(currentPos);
            Integer[][] possibleDirections = checkSurrounding(currentPos);
            Integer[][] possibleDirectionsRand = randomize(possibleDirections);

            shuffleQueue();

            for (Integer[] i : possibleDirectionsRand) { //remember to add rand
                queue.enqueue(i);
                maze[i[0]][i[1]] = 7;
                breakWall(currentPos, i);

            }
        }
    }

    public void shuffleQueue(){
        int r = rdn.nextInt(2);
        if (r == 1 & !queue.isEmpty()) {
            Integer[] ing = queue.dequeue();
            queue.enqueue(ing);
        }
    }

    public void markCurrentPos(Integer[] currentPos){
        maze[currentPos[0]][currentPos[1]] = 2;
    }

    public static void printPos(Integer[] pos){
        System.out.print(pos[0] + ", " + pos[1] + ", ");
    }

    public void breakWall(Integer[] a, Integer[] b){
        int x = (a[0] + b[0])/2;
        int y = (a[1] + b[1])/2;
        maze[x][y] = 2;
    }

    public Integer[][] checkSurrounding(Integer[] currentPos){
        Integer[][] goodToGo = new Integer[4][];
        Integer[][] mayGo = new Integer[4][];
        mayGo[0] = new Integer[] {currentPos[0], currentPos[1] + 2};
        mayGo[1] = new Integer[] {currentPos[0], currentPos[1] - 2};
        mayGo[2] = new Integer[] {currentPos[0] + 2, currentPos[1]};
        mayGo[3] = new Integer[] {currentPos[0] - 2, currentPos[1]};
        int cnt = 0;
        for (Integer[] i: mayGo)
            if (canGo(i)){
                goodToGo[cnt] = i;
                cnt += 1;
            }
        Integer[][] ret = new Integer[cnt][];
        for (int j = 0; j < cnt; j++){
            ret[j] = goodToGo[j];
        }
        return ret;
    }

    public boolean canGo(Integer[] i){
        if (i[0] < 0 | i[1] < 0 | i[0] >= width | i[1] >= height){
            return false;
        }
        if (maze[i[0]][i[1]] == 0){
            return true;
        }
        return false;
    }

    public Integer[][] randomize(Integer[][] pd){ //pd = possible Directions
        int len = pd.length;
        Integer[][] ret = new Integer[len][];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < len; i ++){
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < len; i++){
            ret[i] = pd[list.get(i)];
        }
        return ret;
    }

    public void mapToWorld(){
        for (int i = 0; i < width; i ++){
            for (int j = 0; j < width; j ++){
                if (maze[i][j] == 1) {
                    world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

}
