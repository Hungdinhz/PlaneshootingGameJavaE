package main;

// Quản lý trạng thái game
public class GameManager {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private Background background;

    public GameManager(Player player, Background background) {
        this.player = player;
        this.background = background;
        this.enemies = new ArrayList<>();
        this.explosions = new ArrayList<>();
    }

    public void update() {
        background.update();
        player.update();

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.move();

            // Kiểm tra va chạm với đạn người chơi
            Iterator<Bullet> bulletIterator = player.getBullets().iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (checkCollision(bullet, enemy)) {
                    explosions.add(new Explosion(enemy.getX(), enemy.getY()));
                    bulletIterator.remove();
                    enemyIterator.remove();
                    break;
                }
            }
        }

        explosions.removeIf(explosion -> !explosion.isActive());
    }

    public void draw(Graphics g) {
        background.draw(g);
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        for (Explosion explosion : explosions) {
            explosion.draw(g);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addExplosion(Explosion explosion) {
        explosions.add(explosion);
    }

    private boolean checkCollision(Bullet bullet, Enemy enemy) {
        return bullet.getX() < enemy.getX() + enemy.getWidth() &&
                bullet.getX() + bullet.getWidth() > enemy.getX() &&
                bullet.getY() < enemy.getY() + enemy.getHeight() &&
                bullet.getY() + bullet.getHeight() > enemy.getY();
    }
}
