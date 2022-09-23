package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class PanelGame extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    // game FPS;
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    // Keyhandler
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyH);

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
