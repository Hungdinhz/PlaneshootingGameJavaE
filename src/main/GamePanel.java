package main;

// Vòng lặp game, vẽ màn hình
public class GamePanel extends JPanel implements Runnable {
    private GameManager gameManager;
    private boolean running;
    private Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setFocusable(true);
        this.running = true;

        Player player = new Player(375, 500, 50, 50, null);
        Background background = new Background(null, 800, 600, 2);
        gameManager = new GameManager(player, background);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
        });

        startGame();
    }

    private void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            gameManager.update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.draw(g);
    }
}
