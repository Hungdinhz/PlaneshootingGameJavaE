package entities;

import javax.swing.*;
import java.awt.*;

// Đạn của người chơi
public class Bullet {
    private double x, y;
    private int width, height;
    private final Image image = new ImageIcon("assets/images/bullet1.png").getImage();
    private double speed; // Tốc độ đạn

    public Bullet(double x, double y, int width, int height, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        this.y -= speed; // Đạn bay lên
    }

    public void updateMovement(){

    }

    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

    public boolean isOutOfScreen() {
        return y + height < 0; // Kiểm tra nếu đạn ra khỏi màn hình
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
