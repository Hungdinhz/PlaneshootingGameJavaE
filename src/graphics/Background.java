package graphics;
import java.awt.Graphics;
import java.awt.Image;
// Xử lí nền game
public class Background {
    private Image image;
    private int y1, y2;
    private int speed;
    private int width, height;

    public Background(Image image, int width, int height, int speed) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.y1 = 0;
        this.y2 = -height;
    }

    public void update() {
        y1 += speed;
        y2 += speed;
        if (y1 >= height) {
            y1 = y2 - height;
        }
        if (y2 >= height) {
            y2 = y1 - height;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, 0, y1, width, height, null);
        g.drawImage(image, 0, y2, width, height, null);
    }
}
