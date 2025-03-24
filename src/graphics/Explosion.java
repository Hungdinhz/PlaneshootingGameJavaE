package graphics;

import java.awt.*;
import javax.swing.*;

public class Explosion {
    private Image explosionImage;
    private int x, y;

    public Explosion(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        explosionImage = new ImageIcon(imagePath).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(explosionImage, x, y, null);
    }
}
