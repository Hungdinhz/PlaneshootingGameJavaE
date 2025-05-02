package main;

import base.Bullet;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GameManager implements KeyListener {

    private boolean checkWin = false;
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
    private double speedEnemy = 35;
    private int hpEnemy = 3;

    // BOSS
    private Boss boss;
    private final int bossAppearThreshold = 2;
    private boolean bossAppeared = false;
    private int bossWidth = WIDTH * 6 / 10;
    private int bossHeight = bossWidth * 8 / 10;
    private double bossX;
    private double bossY = - bossHeight;
    private Image imageBoss = new ImageIcon("assets/images/enemy1.png").getImage();
    private double speedBoss = 15;
    private int hpBoss = 100;

    // Bullet Player
    private ArrayList<Bullet> playerBullets = new ArrayList<>();
    private int widthBulletPlayer = 15;
    private int heightBulletPlayer = 40;
    private double speedBulletPlayer = 15;
    private Image imgBulletPlayer =  new ImageIcon("assets/images/bullet1.png").getImage();

    //Bullet Enemy
    private ArrayList<Bullet> enemyBullets = new ArrayList<>();
    private int widthBulletEnemy = 20;
    private int heightBulletEnemy = 20;
    private double speedBulletEnemy = 5;
    private Image imgBulletEnemy =  new ImageIcon("assets/images/bullet2.png").getImage();

    // Bullet Boss
    //private ArrayList<EnemyBullet> bossBullets = new ArrayList<>();
    private ArrayList<Bullet> bossBullets = new ArrayList<>();
    private int widthBulletboss = 20;
    private int heightBulletboss = 20;
    private double speedBulletboss = 8;
    private Image imgBulletboss =  new ImageIcon("assets/images/bullet2.png").getImage();

    // damage
    private int damagePlayer = 1;
    private int damageBoss = 1;
    private int damageEnemy = 1;

    // Timer
    private double bulletTimerPlayer = 0;
    private double timePerShoot = 0.3;

    private double timeCreateEnemy = 1.5;
    private double timerEnemy = 0;

    private double bulletTimerEnemy = 0;
    private double timePerShootE = 4.5;

    private double bulletTimerboss = 0;
    private double timePerShootBoss = 1;

    // Count
    private int enemyKilledCount = 0;


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
        enemys.add(new Enemy(enemyX, enemyY, enemyWidth, enemyHeight, speedEnemy, imageEnemy, hpEnemy));
    }

    public void creatBoss(){
        bossX = (WIDTH / 2) - (bossWidth / 2);
        boss = new Boss(bossX, bossY, bossWidth, bossHeight, speedBoss, imageBoss, hpBoss);
    }

    public void draw(Graphics g) {
        // draw background
        background.draw(g, WIDTH, HEIGHT);

        // draw player
        player.draw(g);

        // draw enemy
        for(int i = 0; i < enemys.size(); i++){
            Enemy e = enemys.get(i);
            e.draw(g);
        }

        // draw boss
        if(bossAppeared){
            boss.draw(g);

            // draw bullet's enemy
            for (Bullet bullet : bossBullets) {
                bullet.draw(g);
            }
        }

        // draw bullet's player
        for (Bullet playerBullet : playerBullets) {
            playerBullet.draw(g);
        }

        // draw bullet's enemy
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.draw(g);
        }

        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER! SCORE = " + score, 0, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Press R to Restart", 200, HEIGHT / 2 + 40);
        } else if(checkWin){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("YOU WINNN! SCORE = " + score, 0, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Press R to Restart", 200, HEIGHT / 2 + 40);
        }else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 20, 30);
        }
    }

    public void update(double delta) {
        checkCollisions();
        player.update(delta);
        if(enemyKilledCount == bossAppearThreshold && !bossAppeared){
            creatBoss();
            bossAppeared = true;
        }

        // cap nhat vi tri dich
        for(int i = 0; i < enemys.size(); i++){
            enemys.get(i).update(delta);
        }

        // cap nhap vi tri boss
        if(bossAppeared){
            boss.update(delta);
        }

        // player ban dan
        bulletTimerPlayer += delta;
        if(bulletTimerPlayer >= timePerShoot){
            bulletTimerPlayer = 0;
            playerBullets.add(player.shoot(widthBulletPlayer, heightBulletPlayer, speedBulletPlayer, imgBulletPlayer, damagePlayer));
        }

        // tao dich sau 1s
        if(!bossAppeared) {
            timerEnemy += delta;
            if (timerEnemy >= timeCreateEnemy) {
                timerEnemy = 0;
                creatEnemy();
            }
        }

        // enemys ban dan
        bulletTimerEnemy += delta;
        if(bulletTimerEnemy >= timePerShootE){
            bulletTimerEnemy = 0;
            for (Enemy enemy : enemys) {
                enemyBullets.add(enemy.shoot(widthBulletEnemy, heightBulletEnemy, speedBulletEnemy, imgBulletEnemy, damageEnemy));
            }
        }

        // boss ban dan
        if(bossAppeared) {
            bulletTimerboss += delta;
            if (bulletTimerboss >= timePerShootBoss) {
                bulletTimerboss = 0;
                bossBullets.add(boss.shootNormal(boss.getX() + boss.getWidth() / 2 - widthBulletboss / 2, boss.getY() + boss.getHeight(), widthBulletboss, heightBulletboss, speedBulletboss, imgBulletboss, damageBoss)); // dan o giua
                bossBullets.add(boss.shootHoming(boss.getX() - widthBulletboss / 2, boss.getY() + boss.getHeight(), widthBulletboss, heightBulletboss, speedBulletboss, imgBulletboss, damageBoss)); // dan ben trai
                bossBullets.add(boss.shootHoming(boss.getX() + boss.getWidth() - widthBulletboss / 2, boss.getY() + boss.getHeight(), widthBulletboss, heightBulletboss, speedBulletboss, imgBulletboss,damageBoss)); // dan ben phai
            }
        }

        // delete bullet khi dna ra khoi man hinh
        for (int i = 0; i < playerBullets.size(); i++) {
            playerBullets.get(i).update(delta);
            if (playerBullets.get(i).isOutOfScreen()) {
                playerBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet bullet = enemyBullets.get(i);
            // Kiểm tra và sử dụng pattern variable
            if (bullet instanceof HomingBullet homingBullet) {
                // Nếu đạn là HomingBullet, cập nhật theo player
                homingBullet.update(player, delta);  // Truyền player vào để đạn đuổi theo player
            } else {
                // Đối với các loại đạn khác, gọi phương thức update chung
                bullet.update(delta);  // Phương thức update chung
            }

            // Kiểm tra nếu đạn ra ngoài màn hình
            if (bullet.isOutOfScreen()) {
                enemyBullets.remove(i);
                i--;
            }
        }

        for (int i = bossBullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bossBullets.get(i);

            // Kiểm tra và sử dụng pattern variable
            if (bullet instanceof HomingBullet homingBullet) {
                // Nếu đạn là HomingBullet, cập nhật theo player
                homingBullet.update(player, delta);  // Truyền player vào để đạn đuổi theo player
            } else {
                // Đối với các loại đạn khác, gọi phương thức update chung
                bullet.update(delta);  // Phương thức update chung
            }

            // Kiểm tra nếu đạn ra ngoài màn hình
            if (bullet.isOutOfScreen()) {
                bossBullets.remove(i);
            }
        }



    }

    // Kiểm tra va chạm
    public void checkCollisions(){
        //Kiểm tra đạn của player va chạm với kẻ địch
        Iterator<Bullet> bulletPlayer = playerBullets.iterator();
        while (bulletPlayer.hasNext()) {
            Bullet playerBullet = bulletPlayer.next();
            Iterator<Enemy> enemyIterator = enemys.iterator();
            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();
                if (playerBullet.getBounds().intersects(e.getBounds())) {
                    e.takeDamage(playerBullet.getDamage()); // Gây sát thương cho kẻ địch
                    bulletPlayer.remove(); // Xóa viên đạn an toàn

                    if (e.isDead()) { // Nếu kẻ địch chết
                        enemyIterator.remove(); // ✅ Xóa kẻ địch an toàn
                        score += 10; // ✅ Cộng điểm khi tiêu diệt kẻ địch
                        enemyKilledCount++;
                    }

                    break; // Dừng kiểm tra các kẻ địch khác cho viên đạn này
                }
            }
        }

        // Kiểm tra kẻ địch va chạm với player
        Iterator<Enemy> enemyIterator = enemys.iterator();
        while (enemyIterator.hasNext()) {
            Enemy e = enemyIterator.next();
            if (e.getBounds().intersects(player.getBounds())) {
                player.takeDamage(damageEnemy * 2);
                enemyIterator.remove();
                score += 20;// Xóa an toàn
                enemyKilledCount++;
                if(player.isDead()){
                    gameOver = true;
                    return;
                }
            }
        }

        // Kiểm tra đạn của enemy va chạm với plauer
        for (Enemy e : enemys) {
            Iterator<Bullet> bulletEIterator = enemyBullets.iterator();
            while (bulletEIterator.hasNext()) {
                Bullet eb = bulletEIterator.next();
                if (eb.getBounds().intersects(player.getBounds())) {
                    player.takeDamage(eb.getDamage());
                    bulletEIterator.remove();
                    if(player.isDead()){
                        gameOver = true;
                        return;
                    }
                }
            }
        }

        if(bossAppeared) {
            //  Kiểm tra đạn của player va chạm với boss
            Iterator<Bullet> bulletP = playerBullets.iterator();
            while (bulletP.hasNext()) {
                Bullet pb = bulletP.next();
                if (pb.getBounds().intersects(boss.getBounds())) {
                    boss.takeDamage(pb.getDamage());
                    bulletP.remove();
                    if (boss.isDead()) {
                        checkWin = true;
                        return;
                    }
                }
            }

            //Kiểm tra đạn của boss va chạm với player
            Iterator<Bullet> bulletB = bossBullets.iterator();
            while (bulletB.hasNext()) {
                Bullet bb = bulletB.next();
                if (bb.getBounds().intersects(player.getBounds())) {
                    player.takeDamage(bb.getDamage());
                    bulletB.remove();
                    if (player.isDead()) {
                        gameOver = true;
                        return;
                    }
                }
            }

            //Kiểm tra boss va chạm với player
            if (boss.getBounds().intersects(player.getBounds())) {
                player.takeDamage(hpPlayer);
                gameOver = true;
                return;
            }
        }

        // may bay cham vien la thua
        /*if(player.getX() < 0 || player.getX() + playerWidth > WIDTH || player.getY() < 0 || player.getY() + playerHeight > HEIGHT){
            gameOver = true;
        }*/

    }

    public void resetGame() {
        checkWin = false;
        gameOver = false;
        score = 0;

        // Reset player
        player.setX(playerX);
        player.setY(playerY);
        player.setHp(hpPlayer);

        // Clear mọi đối tượng
        enemys.clear();
        playerBullets.clear();
        enemyBullets.clear();
        bossBullets.clear();

        // Reset boss
        bossAppeared = false;
        enemyKilledCount = 0;
        boss = null;

        // Reset timer
        bulletTimerPlayer = 0;
        timerEnemy = 0;
        bulletTimerEnemy = 0;
        bulletTimerboss = 0;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Bắn 3 viên đạn cùng lúc
            MultiBullet multiBullet = new MultiBullet(player.getX(), player.getY(), widthBulletPlayer , heightBulletPlayer, speedBulletPlayer, imgBulletPlayer,  damagePlayer, 4);
            playerBullets.addAll(multiBullet.getBullets());
        }
        // Thêm reset game bằng phím R
        if ((checkWin || gameOver) && e.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
           //start();
        }
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

    public boolean isCheckWin() {
        return checkWin;
    }

    public void setCheckWin(boolean checkWin) {
        this.checkWin = checkWin;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
