package entities;

import java.awt.*;

// Đạn của địch
public class EnemyBullet {
    private int x, y;
    private int width, height;
    private int speed;

    public EnemyBullet(int x, int y, int width, int height, int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move(){
        this.y += speed;
    }

    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

