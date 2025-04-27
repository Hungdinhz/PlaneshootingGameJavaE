package entities;

import base.Plane;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Plane {
    private boolean movingLeft, movingRight, movingUp, movingDown;

    public Player(double x, double y, int width, int height, Image image, double speed, int hp) {
        super(x, y, width, height, speed, image, hp);
    }

    private void move(double dx, double dy) {
        setX(getX() + dx * getSpeed());
        setY(getY() + dy * getSpeed());
    }

    private void updateMovement(double delta) {
        double dx = 0, dy = 0;
        if (movingLeft) dx = -1;
        if (movingRight) dx = 1;
        if (movingUp) dy = -1;
        if (movingDown) dy = 1;

        move(dx * delta, dy * delta);
    }

    @Override
    public void update(double delta) {
        updateMovement(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    public PlayerBullet shoot(int widthB, int heightB, double speedB, Image imageB) {
        return new PlayerBullet(getX() + (double) getWidth() / 2 - 5, getY(), widthB, heightB, speedB, imageB);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> movingLeft = true;
            case KeyEvent.VK_RIGHT -> movingRight = true;
            case KeyEvent.VK_UP -> movingUp = true;
            case KeyEvent.VK_DOWN -> movingDown = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> movingLeft = false;
            case KeyEvent.VK_RIGHT -> movingRight = false;
            case KeyEvent.VK_UP -> movingUp = false;
            case KeyEvent.VK_DOWN -> movingDown = false;
        }
    }

}
