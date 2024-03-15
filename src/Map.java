import java.awt.*;
import java.security.SecureRandom;

public class Map {

    GamePanel gp;
    SecureRandom random = new SecureRandom();
    public int[][] grid;
    int p = 4;
    public Map (GamePanel gp){
        this.gp = gp;
    }

    public void setupMap (){
        grid = new int[gp.screenHeight][gp.screenWidth];

        for (int y = 0; y < gp.screenHeight; y++) {
            for (int x = 0; x < gp.screenWidth; x++) {
                grid[y][x] = random.nextInt(0,2);
            }
        }
    }

    public void update(){
        int[][] updatedGrid = new int[gp.screenHeight][gp.screenWidth];

        for (int y = 0; y < gp.screenHeight; y++) {
            for (int x = 0; x < gp.screenWidth; x++) {
                updatedGrid[y][x] = checkAdjacentTiles(y,x);
            }
        }
        grid = updatedGrid;
        if (p == 4){
            p = 3;
        } else {
            p = 4;
        }
    }

    public int checkAdjacentTiles (int y, int x){
        int wallCount = 0;

        try {
            if (grid[y][x - 1] == 1) {
                wallCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (grid[y][x + 1] == 1) {
                wallCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        for(int i = 0; i<3; i++){
            try {
                if (grid[y - 1][x - 1 + i] == 1) {
                    wallCount++;
                }
            }catch (ArrayIndexOutOfBoundsException e){}
        }

        for(int i = 0; i<3; i++){
            try {
                if (grid[y + 1][x - 1 + i] == 1) {
                    wallCount++;
                }
            }catch (ArrayIndexOutOfBoundsException e){}
        }
        return (wallCount > p) ? 1 : 0;
    }

    public void draw (Graphics g2){
        for (int y = 0; y < gp.gridHeight; y++){
            for (int x = 0; x < gp.gridWidth; x++){
                if (grid[y][x] == 1){
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(Color.BLACK);
                }
                g2.fillRect(x * gp.tileSize, y * gp.tileSize, gp.tileSize, gp.tileSize);
            }
        }
    }
}
