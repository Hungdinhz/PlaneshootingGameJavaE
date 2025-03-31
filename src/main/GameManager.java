package main;

import entities.Bullet;
import entities.Enemy;
import entities.EnemyBullet;
import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GameManager implements KeyListener {
    private boolean gameOver = false;
    public int score = 0;
    JLabel label;

    private GamePanel gamePanel;
    private graphics.Background background;

    private static final int WIDTH = 700;
    private static final int HEIGHT = 800;
    private String bgPath = "assets/images/background.jpg";

    // Player
    private Player player;
    private int playerWidth = 40;
    private int playerHeight = 46;
    private double playerX = WIDTH / 2 - playerWidth / 2;
    private double playerY = HEIGHT - 100;
    private Image imagePlayer = new ImageIcon("assets/images/plane1.png").getImage();
    private double speedPlayer = 500;
    private int hpPlayer = 5;


    // Enemy
    private ArrayList<Enemy> enemys = new ArrayList<>();
    private int enemyWidth = 60;
    private int enemyHeight = 60;
    private double enemyX;
    private double enemyY = 0;
    private Image imageEnemy = new ImageIcon("assets/images/enemy1.png").getImage();
    private double speedEnemy = 30;
    private int hpEnemy = 3;

    // Timer
    private double bulletTimerPlayer = 0;
    private double timePerShoot = 0.2;

    private double timeCreateEnemy = 1.5;
    private double timerEnemy = 0;

    private double bulletTimerEnemy = 0;
    private double timePerShootE = 4.5;

    public GameManager() {
        gamePanel = new GamePanel(this); // Truyền this vào GamePanel
    }

    public void start() {
        gamePanel.init();
    }

    public void createObject() {
        background = new graphics.Background(bgPath);
        player = new Player(playerX, playerY, playerWidth, playerHeight, imagePlayer, speedPlayer, hpPlayer);
    }

    public void creatEnemy(){
        // random x
        enemyX = Math.random() * (WIDTH - enemyWidth);

        enemys.add(new Enemy(enemyX, enemyY, enemyWidth, enemyHeight, imageEnemy, speedEnemy, hpEnemy));
    }

    public void draw(Graphics g) {
        // draw background
        background.draw(g, WIDTH, HEIGHT);

        // draw player
        player.draw(g);
        for(int i = 0; i < enemys.size(); i++){
            Enemy e = enemys.get(i);
            e.draw(g);
        }

        // draw bullet's player
        for(int i = 0; i < player.getBullets().size(); i++){
            Bullet bullet = player.getBullets().get(i);
            bullet.draw(g);
        }

        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER! SCORE = " + score, 0, HEIGHT / 2);
        }else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 20, 30);
        }
    }

    public void update(double delta) {
        checkCollisions();
        player.update(delta);

        // cap nhat vi tri dich
        for(int i = 0; i < enemys.size(); i++){
            enemys.get(i).update(delta);
        }

        // player ban dan
        bulletTimerPlayer += delta;
        if(bulletTimerPlayer >= timePerShoot){
            bulletTimerPlayer = 0;
            player.shoot();
        }

        // tao dich sau 1s
        timerEnemy += delta;
        if(timerEnemy >= timeCreateEnemy){
            timerEnemy = 0;
            creatEnemy();
        }

        // enemys ban dan
        bulletTimerEnemy += delta;
        if(bulletTimerEnemy >= timePerShootE){
            bulletTimerEnemy = 0;
            for(int i = 0; i < enemys.size(); i++){
                enemys.get(i).shoot();
            }
        }

    }

    public void checkCollisions(){
        Iterator<Bullet> bulletIterator = player.getBullets().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            Iterator<Enemy> enemyIterator = enemys.iterator();
            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();
                if (bullet.getBounds().intersects(e.getBounds())) {
                    e.takeDamage(1); // Gây sát thương cho kẻ địch
                    bulletIterator.remove(); // Xóa viên đạn an toàn

                    if (e.isDead()) { // Nếu kẻ địch chết
                        enemyIterator.remove(); // ✅ Xóa kẻ địch an toàn
                        score += 10; // ✅ Cộng điểm khi tiêu diệt kẻ địch
                    }

                    break; // Dừng kiểm tra các kẻ địch khác cho viên đạn này
                }
            }
        }

        Iterator<Enemy> enemyIterator = enemys.iterator();
        while (enemyIterator.hasNext()) {
            Enemy e = enemyIterator.next();
            if (e.getBounds().intersects(player.getBounds())) {
                player.takeDamage(2);
                enemyIterator.remove();
                score += 20;// Xóa an toàn
                if(player.isDead()){
                    gameOver = true;
                }
            }
        }


        for (Enemy e : enemys) {
            Iterator<EnemyBullet> bulletEIterator = e.getBullets().iterator();
            while (bulletEIterator.hasNext()) {
                EnemyBullet eb = bulletEIterator.next();
                if (eb.getBounds().intersects(player.getBounds())) {
                    player.takeDamage(1);
                    bulletEIterator.remove();
                    if(player.isDead()){
                        gameOver = true;
                    }
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    // Getter để lấy kích thước màn hình
    public int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
