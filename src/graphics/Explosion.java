package graphics;

import java.awt.*;

//Hiệu ứng nổ
public class Explosion {
    private int x, y;
    private int size;
    private int frame;
    private boolean active;
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW};

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 30;
        this.frame = 0;
        this.active = true;
    }

    public void update() {
        if (frame < 10) {
            size += 5;
            frame++;
        } else {
            active = false;
        }
    }

    public void draw(Graphics g) {
        if (active) {
            g.setColor(colors[frame % colors.length]);
            g.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }

    public boolean isActive() {
        return active;
    }
}
