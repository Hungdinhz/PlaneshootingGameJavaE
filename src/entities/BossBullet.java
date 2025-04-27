package entities;

import base.Bullet;
import main.GameManager;

import java.awt.*;

public class BossBullet extends Bullet {

    public BossBullet(double x, double y, int width, int height, double speed, Image image) {
        super(x, y, width, height, speed / 2, image);
    }

    @Override
    public void update(double delta) {
        setY(getY() + getSpeed());
    }

    public void update(Player player, double delta){
        double px = player.getX();

        double dx = (px - getX())* delta;

        setX(getX() + dx);

        setY(getY() + getSpeed());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    public boolean isOutOfScreen() {
        return getY() > GameManager.getHeight();
    }
}
