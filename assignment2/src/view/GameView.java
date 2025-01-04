package view;

import javax.swing.JFrame;

public class GameView extends JFrame {
    public GameView() {
        setTitle("XO ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}