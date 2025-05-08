package base;

import java.awt.*;

public abstract class Plane extends EntityGame {
    private int hp;
    private boolean isTakeDame;

    public Plane(double x, double y, int width, int height, double speed, Image image, int hp) {
        super(x, y, width, height, speed, image);
        this.hp = hp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        setTakeDame(true);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public boolean isTakeDame() {
        return isTakeDame;
    }

    public void setTakeDame(boolean takeDame) {
        isTakeDame = takeDame;
    }
}
