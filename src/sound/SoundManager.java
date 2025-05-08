package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    private static Clip backgroundClip;

    // Phát nhạc nền (lặp lại) từ file nằm trong src/assets/sounds/
    public static void playBackgroundMusic(String relativePath) {
        try {
            URL soundURL = SoundManager.class.getResource("/" + relativePath);
            if (soundURL == null) {
                System.err.println("Không tìm thấy file âm thanh: " + relativePath);
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInput);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // Lặp vô hạn
        } catch (UnsupportedAudioFileException e) {
            System.err.println("File âm thanh không đúng định dạng WAV.");
        } catch (IOException e) {
            System.err.println("Lỗi đọc file âm thanh.");
        } catch (LineUnavailableException e) {
            System.err.println("Không thể phát âm thanh: thiết bị âm thanh bận hoặc không sẵn sàng.");
        }
    }

    // Dừng nhạc nền
    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }
}
