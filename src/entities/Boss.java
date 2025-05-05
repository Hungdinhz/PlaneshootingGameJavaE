package entities;

import base.Bullet;
import base.Plane;
import main.GameManager;

import java.awt.*;

public class Boss extends Plane {
    private boolean moveRight;

    public Boss(double x, double y, int width, int height, double speed, Image image, int hp) {
        super(x, y, width, height, speed, image, hp);
    }

    private void move(double dx, double dy) {
        setX(getX() + dx * getSpeed());
        setY(getY() + dy * getSpeed());
    }

    private void updateMovement(double delta) {
        boolean check;
        if(getY() > 10){
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
            move(0, delta); // Di chuyển xuống
        }
    }

    @Override
    public void update(double delta) {
        updateMovement(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int)getX(), (int)getY(), getWidth(), getHeight(), null);
    }

    public Bullet shootHoming(double x, double y, int widthB, int heightB, double speedB, Image imageB, int dame) {
        return new HomingBullet(x, y, widthB, heightB, speedB, imageB, dame);
    }

    public Bullet shootNormal(double x, double y, int widthB, int heightB, double speedB, Image imageB, int dame) {
        return new EnemyBullet(x, y, widthB, heightB, speedB, imageB, dame);
    }

}
