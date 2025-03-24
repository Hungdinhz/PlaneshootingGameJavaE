package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

// Máy bay người chơi
public class Player {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final Image image;
    private int speed = 30;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public Player(int x, int y, int width, int height, Image image){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void move(int dx, int dy) {
        this.x += dx * speed;
        this.y += dy * speed;
    }

    // Phương thức bắn đạn
    public void shoot() {
        bullets.add(new Bullet(x + width / 2, y, 5, 10, 20)); // Thêm viên đạn mới
    }

    // Phương thức vẽ người chơi và đạn lên màn hình
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        /*for (Bullet bullet : bullets) {
            bullet.draw(g);
        }*/
    }

    // Cập nhật vị trí của đạn (để đạn di chuyển)
    public void update() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (bullets.get(i).isOutOfScreen()) {
                bullets.remove(i); // Xóa đạn nếu ra khỏi màn hình
                i--;
            }
        }
    }

    // Xử lý khi nhấn phím
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) move(-1, 0);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) move(1, 0);
        if (e.getKeyCode() == KeyEvent.VK_UP) move(0, -1);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) move(0, 1);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) shoot();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
