package main;

/*
    Vẽ nền, máy bay địch, máy bay người chơi, đạn bắn ra
 */
import entities.Enemy;
import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // Man hinh
    private JFrame frame;
    private GameManager gm;

    public GamePanel(GameManager gm){
        this.gm = gm;
    }

    public void init() {
        gm.createObject();

        frame = new JFrame("Plane Shooter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gm.getWidth(), gm.getHeight());
        frame.add(this);
        frame.setVisible(true);

        frame.addKeyListener(gm);
        frame.setFocusable(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gm.draw(g);
    }
}
