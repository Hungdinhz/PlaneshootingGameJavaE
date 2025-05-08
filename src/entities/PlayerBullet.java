package entities;

import base.Bullet;

import java.awt.*;

public class PlayerBullet extends Bullet {
    public PlayerBullet(double x, double y, int width, int height, double speed, Image image, int dame) {
        super(x, y, width, height, speed, image, dame);
        setDy(-1);
    }

    @Override
    public void update(double delta) {
        move(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

}
