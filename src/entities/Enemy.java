package entities;

import base.Bullet;
import base.Plane;
import main.GameManager;

import java.awt.*;

public class Enemy extends Plane {
    private boolean moveRight;

    public Enemy(double x, double y, int width, int height, double speed, Image image, int hp) {
        super(x, y, width, height, speed, image, hp);
    }

    private void move(double dx, double dy) {
        setX(getX() + dx * getSpeed());
        setY(getY() + dy * getSpeed());
    }

    private void updateMovement(double delta) {

        if(getY() > (double) GameManager.getHeight() / 3){
            if(getX() < 0){
                moveRight = true;
            }
            if(getX() + getWidth() > GameManager.getWidth()){
                moveRight = false;
            }
            if(moveRight){
                move(delta, 0);
            }else{
                move(-delta, 0);
            }
        }else {
            if(getX() < 0){
                moveRight = true;
            }
            if(getX() + getWidth() > GameManager.getWidth()){
                moveRight = false;
            }
            if(moveRight){
                move(delta, delta);
            }else{
                move(-delta, delta);
            }
        }
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
        return new EnemyBullet(getX() + (double) getWidth() / 2 - 10, getY() + getHeight(), widthB, heightB, speedB, imageB, dame);
    }

}
