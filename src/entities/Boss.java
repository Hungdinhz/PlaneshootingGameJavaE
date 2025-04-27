package entities;

import base.Plane;

import java.awt.*;

public class Boss extends Plane {

    public Boss(double x, double y, int width, int height, double speed, Image image, int hp) {
        super(x, y, width, height, speed, image, hp);
    }

    private void move(double dx, double dy) {
        setY(getY() + dy * getSpeed());
    }

    private void updateMovement(double delta) {
        move(0, delta); // Di chuyển xuống
    }

    @Override
    public void update(double delta) {
        updateMovement(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int)getX(), (int)getY(), getWidth(), getHeight(), null);
    }

    public BossBullet shoot(double x, double y, int widthB, int heightB, double speedB, Image imageB) {
        return new BossBullet(x, y, widthB, heightB, speedB, imageB);
    }

}
