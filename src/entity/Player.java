package entity;

import main.KeyHandler;
import main.PanelGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
    private int x = 100;
    private int y = 100;
    private final int speed = 4;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction = "down";
    private int  spriteCouter = 0;
    private int spriteNum = 1;

    private PanelGame panelGame;
    private KeyHandler keyHandler;

    public Player(PanelGame panelGame, KeyHandler keyHandler) {
        this.panelGame = panelGame;
        this.keyHandler = keyHandler;
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Image/player/boy_right_2.png"));
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
        if (keyHandler.isUpPressed() == true || keyHandler.isDownPressed() == true
            || keyHandler.isRightPressed() == true || keyHandler.isLeftPressed() == true) {
            if(keyHandler.isUpPressed() == true) {
                direction = "up";
                y -= speed;
            } else if (keyHandler.isDownPressed() == true) {
                direction = "down";
                y += speed;
            } else if (keyHandler.isRightPressed() == true) {
                direction = "right";
                x += speed;
            } else if (keyHandler.isLeftPressed() == true) {
                direction = "left";
                x -= speed;
            }
            spriteCouter ++;
            if (spriteCouter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCouter = 0;
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
        g2.drawImage(image, x, y, panelGame.tileSize, panelGame.tileSize, null);
    }
}
