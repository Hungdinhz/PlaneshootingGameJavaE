package graphics;

import java.awt.*;
import javax.swing.*;

public class Background {
    private Image backgroundImage;

    public Background(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    public void draw(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, null);
    }
}

