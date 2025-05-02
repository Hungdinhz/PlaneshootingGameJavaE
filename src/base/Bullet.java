package base;

import java.awt.*;

public abstract class Bullet extends EntityGame {
    private double dx = 0;
    private int damage;

    public Bullet(double x, double y, int width, int height, double speed, Image image, int damage) {
        super(x, y, width, height, speed, image);
        this.damage = damage;
    }

    public abstract boolean isOutOfScreen();

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
