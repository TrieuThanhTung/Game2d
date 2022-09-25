package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class PanelGame extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public int screenHeight = tileSize * maxScreenRow; // 576 pixel

    // world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int screenWorldWidth = maxWorldCol * tileSize;
    public final int screenWorldHeight = maxWorldRow * tileSize;

    // game FPS;
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    // Key handler
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public Player player = new Player(this, keyH);
    TileManager tileManager = new TileManager(this);

    public PanelGame() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            // System.out.println("The game loop is running\n");
            long startTime = System.nanoTime();
            // 1. Update information
            update();
            // 2. draw the screen with the update information
            repaint();
            long time = System.nanoTime() - startTime;
            if(time < TARGET_TIME) {
                long sleep = (TARGET_TIME - time) / 1000000;
                sleep(sleep);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

    private void sleep(long speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
