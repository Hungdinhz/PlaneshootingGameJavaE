package entities;

import base.Bullet;
import base.Plane;
import main.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Plane {
    private boolean movingLeft, movingRight, movingUp, movingDown;

    public Player(double x, double y, int width, int height, Image image, double speed, int hp) {
        super(x, y, width, height, speed, image, hp);
    }

    private void move(double dx, double dy) {
        double newX = getX() + dx * getSpeed();
        double newY = getY() + dy * getSpeed();

        // Giới hạn trong màn hình
        if (newX < 0) newX = 0;
        if (newX + getWidth() > GameManager.getWidth())
            newX = GameManager.getWidth() - getWidth();

        if (newY < 0) newY = 0;
        if (newY + getHeight() > GameManager.getHeight())
            newY = GameManager.getHeight() - getHeight();

        setX(newX);
        setY(newY);
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

    public Bullet shoot(int widthB, int heightB, double speedB, Image imageB, int dame) {
        return new PlayerBullet(getX() + (double) getWidth() / 2 - 5, getY(), widthB, heightB, speedB, imageB, dame);
    }

    public boolean isOutOfScreen() {
        return getX() < 0 || getX() + getWidth() > GameManager.getWidth()
                || getY() < 0 || getY() + getHeight() > GameManager.getHeight();
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
