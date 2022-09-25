package entity;

import main.KeyHandler;
import main.PanelGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private int spriteCounter = 0;
    private int spriteNum = 1;

    public final int screenX;
    public final int screenY;

    private PanelGame panelGame;
    private KeyHandler keyHandler;

    public Player(PanelGame panelGame, KeyHandler keyHandler) {
        this.panelGame = panelGame;
        this.keyHandler = keyHandler;

        screenX = panelGame.screenWidth / 2 - panelGame.tileSize;
        screenY = panelGame.screenHeight / 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = panelGame.tileSize * 23;
        worldY = panelGame.tileSize * 21;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/image/player/boy_right_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
        if (keyHandler.isUpPressed() || keyHandler.isDownPressed()
            || keyHandler.isRightPressed() || keyHandler.isLeftPressed()) {
            if(keyHandler.isUpPressed()) {
                direction = "up";
                worldY -= speed;
            } else if (keyHandler.isDownPressed()) {
                direction = "down";
                worldY += speed;
            } else if (keyHandler.isRightPressed()) {
                direction = "right";
                worldX += speed;
            } else if (keyHandler.isLeftPressed()) {
                direction = "left";
                worldX -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.green);
//        g2.fillRect(x, y, panelGame.tileSize, panelGame.tileSize);
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, panelGame.tileSize, panelGame.tileSize, null);
    }
}
