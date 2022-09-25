package tile;

import main.PanelGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    PanelGame panelGame;
    Tile tile[];

    int mapTileNum[][];

    public TileManager(PanelGame panelGame) {
        this.panelGame = panelGame;
        tile = new Tile[10];
        mapTileNum = new int[panelGame.maxWorldCol][panelGame.maxWorldRow];

        getTileImage();
        loadMap("/res/map/world1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/image/titles/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < panelGame.maxWorldCol && row < panelGame.maxWorldRow) {
                String line = bufferedReader.readLine();
                while(col < panelGame.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == panelGame.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < panelGame.maxWorldCol && worldRow < panelGame.maxWorldRow) {
            while(worldCol < panelGame.maxWorldCol) {
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * panelGame.tileSize;
                int worldY = worldRow * panelGame.tileSize;
                int screenX = worldX - panelGame.player.worldX + panelGame.player.screenX;
                int screenY = worldY - panelGame.player.worldY + panelGame.player.screenY;

                g2.drawImage(tile[tileNum].image, screenX, screenY, panelGame.tileSize, panelGame.tileSize, null);
                worldCol++;
            }
            if(worldCol == panelGame.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
