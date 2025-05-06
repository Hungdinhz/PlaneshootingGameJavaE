package base;

import main.GameManager;

import java.awt.*;

public abstract class Bullet extends EntityGame {
    private double dx = 0;
    private double dy; // 1-hướng xuống, -1-hướng lên
    private int damage;

    public Bullet(double x, double y, int width, int height, double speed, Image image, int damage) {
        super(x, y, width, height, speed, image);
        this.damage = damage;
    }

    public void move(double delta){
        setX(getX() + dx * delta);
        setY(getY() + dy * getSpeed() *delta);
    }

    public boolean isOutOfScreen() {
        return getX() < 0 || getX() > GameManager.getWidth()
                || getY() < 0 || getY() > GameManager.getHeight();
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
