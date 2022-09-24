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
        tile = new Tile[6];
        mapTileNum = new int[panelGame.maxScreenCol][panelGame.maxScreenRow];

        getTileImage();
        loadMap("/map/map01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Image/titles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Image/titles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Image/titles/water.png"));
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
            while(col < panelGame.maxScreenCol && row < panelGame.maxScreenRow) {
                String line = bufferedReader.readLine();
                while(col < panelGame.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == panelGame.maxScreenCol) {
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
        int col = 0;
        int row = 0;

        int x = 0;
        int y = 0;
        while(col < panelGame.maxScreenCol && row < panelGame.maxScreenRow) {
            while(col < panelGame.maxScreenCol) {
                int tileNum = mapTileNum[col][row];
                g2.drawImage(tile[tileNum].image, x, y, panelGame.tileSize, panelGame.tileSize, null);
                x += panelGame.tileSize;
                col++;
            }
            if(col == panelGame.maxScreenCol) {
                col = 0;
                row++;

                x = 0;
                y += panelGame.tileSize;
            }
        }
    }
}
