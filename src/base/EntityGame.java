package base;

import java.awt.*;

public abstract class EntityGame implements Renderable, Updatable {
    private double x, y;
    private int width, height;
    private double speed;
    private Image image;

    public EntityGame(double x, double y, int width, int height, double speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    // Phương thức getBounds không thay đổi
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
