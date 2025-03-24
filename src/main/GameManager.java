package main;

import entities.Bullet;
import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameManager implements KeyListener {
    private GamePanel gamePanel;
    private graphics.Background background;

    private final int WIDTH = 700;
    private final int HEIGHT = 800;
    private String bgPath = "assets/images/background.jpg";

    // Player
    private Player player;
    private int playerWidth = 40;
    private int playerHeight = 46;
    private int playerX = WIDTH / 2 - playerWidth / 2;
    private int playerY = HEIGHT - 100;
    private Image imagePlayer = new ImageIcon("assets/images/plane1.png").getImage();

    // Butllet's player
    //private Bullet butletPlayer;

    public GameManager() {
        gamePanel = new GamePanel(this); // Truyền this vào GamePanel
    }

    public void start() {
        gamePanel.init();
    }

    public void createObject() {
        background = new graphics.Background(bgPath);
        player = new Player(playerX, playerY, playerWidth, playerHeight, imagePlayer);
        //butletPlayer = new Bullet()
    }

    public void draw(Graphics g) {
        // draw background
        background.draw(g, WIDTH, HEIGHT);

        // draw player
        player.draw(g);

        // draw bullet's player
        for(int i = 0; i < player.getBullets().size(); i++){
            Bullet bullet = player.getBullets().get(i);
            bullet.draw(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
        gamePanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // Getter để lấy kích thước màn hình
    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
