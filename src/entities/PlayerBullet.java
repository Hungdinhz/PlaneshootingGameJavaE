package entities;

import base.Bullet;

import javax.swing.*;
import java.awt.*;

public class PlayerBullet extends Bullet {
    public PlayerBullet(double x, double y, int width, int height, double speed, Image image) {
        super(x, y, width, height, speed, image);
    }

    @Override
    public void update(double delta) {
        setY(getY() - getSpeed());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    public boolean isOutOfScreen() {
        return getY() + getHeight() < 0;
    }
}
