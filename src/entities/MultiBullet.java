package entities;

import base.Bullet;
import java.awt.*;
import java.util.ArrayList;

public class MultiBullet {
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public MultiBullet(double x, double y, int width, int height, double speed, Image image, int dame, int numBullets) {
        // Tạo nhiều viên đạn với vị trí và hướng khác nhau
        for (int i = 0; i < numBullets; i++) {
            // Xác định vị trí và hướng bắn (có thể là khoảng cách giữa các viên đạn)
            Bullet bullet = new PlayerBullet(x, y, width, height, speed, image, dame);
            if(i % 2 != 0){
                bullet.setDx((double) (i*2 + 2) / 2);
            }else{
                bullet.setDx(-(double) (i*2 + 2) / 2);
            }
            bullets.add(bullet);
        }
    }

    public void update(double delta) {
        // Cập nhật tất cả viên đạn trong MultiBullet
        for(int i = 1; i < bullets.size(); i++){ ;
            if(i % 2 != 0){
                bullets.get(i).setDx((double) (i + 2) / 2);
            }else{
                bullets.get(i).setDx(-(double) (i + 2) / 2);
            }
            bullets.get(i).update(delta);
        }
    }

    public void draw(Graphics g) {
        // Vẽ tất cả viên đạn trong MultiBullet
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
