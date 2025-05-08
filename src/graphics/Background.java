package graphics;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class Background {
    private final Image backgroundImage;

    public Background(String imagePath) {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
    }

    public void draw(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, null);
    }
}
