package release;

import release.res.Game;

import javax.swing.*;

public class Main extends JFrame {

    public static final int Width = 800;
    public static final int Height = 480;

    public Main() {
        setSize(Width, Height);
        setTitle("Dino game by s21300");
        Game game = new Game();
        game.addKeyListener(game);
        game.setFocusable(true);
        add(game);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
