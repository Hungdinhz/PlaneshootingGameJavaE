package graphics;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Explosion {
    private ArrayList<Image> explosionFrames;  // Danh sách các khung hình của hiệu ứng nổ
    private int currentFrame;                  // Chỉ số của khung hình hiện tại
    private int x, y;                          // Vị trí vẽ hiệu ứng nổ
    private int width, height;                 // Kích thước của hiệu ứng nổ
    private boolean isFinished;                // Kiểm tra xem hiệu ứng đã hoàn thành chưa
    private double animationSpeed;              // Tốc độ thay đổi khung hình (sử dụng float để dễ dàng thay đổi)
    private double animationCounter;            // Bộ đếm thay đổi khung hình (sử dụng float để đồng bộ với delta)

    public Explosion(int x, int y, ArrayList<String> imagePaths, int width, int height, double animationSpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.animationSpeed = animationSpeed;
        this.explosionFrames = new ArrayList<>();
        this.currentFrame = 0;
        this.animationCounter = 0;
        this.isFinished = false;

        // Tải tất cả các hình ảnh vào danh sách explosionFrames
        for (String path : imagePaths) {
            explosionFrames.add(new ImageIcon(path).getImage());
        }
    }

    // Cập nhật hiệu ứng nổ với delta
    public void update(double delta) {
        if (isFinished) return;

        // Tăng bộ đếm animationCounter dựa trên delta
        animationCounter += delta;

        // Chuyển khung hình khi bộ đếm đạt tốc độ thay đổi
        if (animationCounter >= animationSpeed) {
            animationCounter = 0; // Reset bộ đếm
            currentFrame++; // Chuyển sang khung hình tiếp theo
            if (currentFrame >= explosionFrames.size()) {
                isFinished = true; // Kết thúc khi đã đi qua tất cả các khung hình
            }
        }
    }

    // Vẽ hiệu ứng nổ
    public void draw(Graphics g) {
        if (!isFinished) {
            // Vẽ khung hình hiện tại
            g.drawImage(explosionFrames.get(currentFrame), x, y, width, height, null);
        }
    }

    // Kiểm tra xem hiệu ứng nổ đã kết thúc chưa
    public boolean isFinished() {
        return isFinished;
    }
}
