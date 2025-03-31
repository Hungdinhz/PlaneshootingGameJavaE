package entities;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private double x;
    private double y;
    private final int width;
    private final int height;
    private double speed;
    private int hp;
    private final Image image;
    private ArrayList<EnemyBullet> bullets = new ArrayList<>();

    public Enemy(double x, double y, int width, int height, Image image, double speed, int hp) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
        this.hp = hp;
    }

    public void move(double dx, double dy) {
        this.y += dy * speed;
    }

    public void updateMovement(double delta){

        move(0, delta);
    }

    public void update(double delta){
        updateMovement(delta);

        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).move();
            if (bullets.get(i).isOutOfScreen()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    public void shoot() {
        bullets.add(new EnemyBullet(x + width / 2 - 10, y + height, 20, 20, 3));
    }

    public void takeDamage(int dame){
        hp -= dame;
    }

    public boolean isDead(){
        return hp <= 0;
    }

    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
        for (EnemyBullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<EnemyBullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<EnemyBullet> bullets) {
        this.bullets = bullets;
    }
}
