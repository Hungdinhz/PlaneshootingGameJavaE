package entities;

import main.GameManager;

import javax.swing.*;
import java.awt.*;

// Đạn của địch
public class EnemyBullet {
    private double x, y;
    private int width, height;
    private final Image image = new ImageIcon("assets/images/bullet2.png").getImage();
    private double speed;

    public EnemyBullet(double x, double y, int width, int height, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        this.y += speed;
    }

    public void draw(Graphics g) {
        g.drawImage(image, (int)this.x, (int)this.y, this.width, this.height, null);
    }

    public boolean isOutOfScreen() {
        return y > GameManager.getHeight(); // Kiểm tra nếu đạn ra khỏi màn hình
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
