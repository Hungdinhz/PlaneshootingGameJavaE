package entities;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int x;
    private int y;
    private final int width;
    private final int height;

    private int speed;
    private final Image image;

    private ArrayList<EnemyBullet> bullets;

    public Enemy(int x, int y, int width, int height, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
    }

    public void move() {
        this.y += this.speed;
    }

    public void shoot(){
        bullets.add(new EnemyBullet(x + width/2, y, 5, 10, 5));
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, width, height, null);
        for (EnemyBullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

}
