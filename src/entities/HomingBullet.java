package entities;

import base.Bullet;
import main.GameManager;
import java.awt.*;

public class HomingBullet extends Bullet {

    public HomingBullet(double x, double y, int width, int height, double speed, Image image, int damage) {
        super(x, y, width, height, speed / 2, image, damage);  // Gọi constructor của Bullet với damage
    }

    @Override
    public void update(double delta) {
        setY(getY() + getSpeed());
    }

    public void update(Player player, double delta) {
        double px = player.getX();
        double dx = (px - getX()) * delta;

        setX(getX() + dx);
        update(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    public boolean isOutOfScreen() {
        return getY() > GameManager.getHeight();
    }
}
