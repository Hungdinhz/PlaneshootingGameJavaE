package base;

import java.awt.*;

public abstract class Bullet extends EntityGame{

    public Bullet(double x, double y, int width, int height, double speed, Image image) {
        super(x, y, width, height, speed, image);
    }

    public abstract boolean isOutOfScreen();
}
