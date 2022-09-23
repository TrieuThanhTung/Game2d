package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(768, 576);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Game 2d");
        window.setResizable(false);

        PanelGame panelGame = new PanelGame();
        window.add(panelGame);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panelGame.startGameThread();
    }
}
