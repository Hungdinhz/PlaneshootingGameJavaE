package entities;

import base.Bullet;
import java.awt.*;

public class HomingBullet extends Bullet {

    public HomingBullet(double x, double y, int width, int height, double speed, Image image, int damage, String who) {
        super(x, y, width, height, speed / 1.8, image, damage);
        if(who.equals("enemy")){
            setDy(1);
        }else{
            setDy(-1);
        }
    }

    @Override
    public void update(double delta) {
        move(delta);
    }

    public void update(Player player, double delta) {
        double px = player.getX();
        setDx(px < getX()? -100 : 100);
        update(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

}
