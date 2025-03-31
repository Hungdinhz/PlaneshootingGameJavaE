package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

// Máy bay người chơi
public class Player {
    private double x;
    private double y;
    private final int width;
    private final int height;
    private final Image image;
    private double speed;
    private int hp;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    // move
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public Player(double x, double y, int width, int height, Image image, double speed, int hp){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.speed = speed;
        this.hp = hp;
    }

    public void move(double dx, double dy) {
        this.x += dx * speed;
        this.y += dy * speed;
    }

    public void updateMovement(double delta) {
        double dx = 0, dy = 0;

        if (movingLeft) dx = -1;
        if (movingRight) dx = 1;
        if (movingUp) dy = -1;
        if (movingDown) dy = 1;

        move(dx * delta, dy * delta);
    }

    // Cập nhật vị trí của đạn
    public void update(double delta) {
        updateMovement(delta);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (bullets.get(i).isOutOfScreen()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    // Phương thức bắn đạn
    public void shoot() {
        bullets.add(new Bullet(x + width / 2 - 5, y, 10, 35, 15)); // Thêm viên đạn mới
    }

    public void takeDamage(int dame){
        hp -= dame;
    }

    public boolean isDead(){
        return hp <= 0;
    }

    // Phương thức vẽ người chơi và đạn lên màn hình
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);

        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(g);
        }
    }


    // Xử lý khi nhấn phím
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) movingLeft = true;
        if (key == KeyEvent.VK_RIGHT) movingRight = true;
        if (key == KeyEvent.VK_UP) movingUp = true;
        if (key == KeyEvent.VK_DOWN) movingDown = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) movingLeft = false;
        if (key == KeyEvent.VK_RIGHT) movingRight = false;
        if (key == KeyEvent.VK_UP) movingUp = false;
        if (key == KeyEvent.VK_DOWN) movingDown = false;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
