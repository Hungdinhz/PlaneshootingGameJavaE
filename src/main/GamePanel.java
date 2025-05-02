package main;

/*
    Vẽ nền, máy bay địch, máy bay người chơi, đạn bắn ra
 */

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Game loop
    private Thread gameThread;

    // Man hinh
    private JFrame frame;
    private GameManager gm;

    public GamePanel(GameManager gm){
        this.gm = gm;
    }

    public void init() {
        gm.createObject();

        frame = new JFrame("Air Force 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gm.getWidth(), gm.getHeight());
        frame.add(this);
        frame.setVisible(true);

        frame.addKeyListener(gm);
        frame.setFocusable(true);

        gameLoop();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            gm.draw(g);
    }

    public void gameLoop() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (true) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (!gm.isGameOver() && !gm.isCheckWin()) {
                gm.update(deltaTime); // chỉ update khi chưa win/thua
            }

            repaint();

            try {
                Thread.sleep(1000 / 60); // 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
