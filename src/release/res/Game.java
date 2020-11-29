package release.res;

import release.Main;
import release.things.BackG;
import release.things.Cactus;
import release.things.Cactus1;
import release.things.Dino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static release.things.Cactus.finalY;

public class Game extends JPanel implements KeyListener, Runnable {

    public static int Width, Height;
    final BackG backG;
    final Dino dino;
    final HighScore hs;
    final Cactus cactus;
    final Cactus1 cactus1;
    private boolean welcomeMessage;
    private boolean darkMode;
    private int score;
    private boolean gameRunning;
    private boolean pause;

    public Game() {
        Width = Main.Width;
        Height = Main.Height;
        backG = new BackG();
        gameRunning = false;
        pause = false;
        darkMode = false;
        dino = new Dino();
        welcomeMessage = false;
        cactus = new Cactus();
        cactus1 = new Cactus1();
        hs = new HighScore(".\\highScore.txt");
        setSize(Width, Height);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (darkMode) {
            setBackground(Color.black);
            g.setColor(Color.white);
        } else {
            setBackground(Color.white);
            g.setColor(Color.black);
        }
        g.setFont(new Font("Roboto", Font.BOLD, 20));
        if (hs.readHighScore() != -1) {
            g.drawString("HIGH SCORE = " + hs.readHighScore(), 5, 20);
        } else {
            g.setFont(new Font("Roboto", Font.BOLD, 15));
            g.drawString("Error occurred while reading the high score, check if file is present in " + hs.getFileLocation(), 5, 20);
        }
        g.drawString("SCORE = " + score, 5, 40);
        if (!gameRunning && !welcomeMessage) {
            g.drawString("Press Space to start", getWidth() / 2 - 100, getHeight() / 2);
            g.drawString("Press R to toggle dark mode", getWidth() / 2 - 130, getHeight() / 2 + 20);
        } else if (!gameRunning && !pause) {
            g.drawString("Game over, press Space to restart", getWidth() / 2 - 155, getHeight() / 2);
            g.drawString("Press R to toggle dark mode", getWidth() / 2 - 130, getHeight() / 2 + 20);
        } else if (!gameRunning) {
            g.drawString("Game paused, press P to unpause", getWidth() / 2 - 155, getHeight() / 2);
        }
        else {
            welcomeMessage = true;
        }
        if (!darkMode) {
            backG.create(g);
            dino.create(g);
            if (gameRunning || welcomeMessage) {
                cactus.create(g);
                cactus1.create(g);
            }
        } else {
            backG.createDark(g);
            dino.createDark(g);
            if (gameRunning || welcomeMessage) {
                cactus.createDark(g);
                cactus1.createDark(g);
            }
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            if (gameRunning) {
                dino.jump();
            } else {
                gameRunning = true;
                Cactus.x = Main.Width + 500;
                Cactus1.x = Main.Width;
                if (!darkMode) {
                    Cactus.image = Cactus.generateImage();
                    Cactus1.image = Cactus1.generateImage();
                } else {
                    Cactus.imageDark = Cactus.generateImageDark();
                    Cactus1.imageDark = Cactus1.generateImageDark();
                }
                score = 0;
                new Thread(this).start();
                System.out.println("Game started");
            }
        } else if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            if (!gameRunning) {
                if (!darkMode) {
                    darkMode = true;
                    Cactus.y = finalY;
                    Cactus1.y = finalY;
                    Cactus.imageDark = Cactus.generateImageDark();
                    Cactus1.imageDark = Cactus1.generateImageDark();
                    System.out.println("You're on the dark side");
                } else {
                    darkMode = false;
                    Cactus.y = finalY;
                    Cactus1.y = finalY;
                    Cactus.image = Cactus.generateImage();
                    Cactus1.image = Cactus1.generateImage();
                }
            }
        }
        else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P'){
            if (gameRunning){
                gameRunning = false;
                pause = true;
            } else {
                gameRunning = true;
                pause = false;
                new Thread(this).start();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void run() {
        while (gameRunning) {
            if (!darkMode) {
                if (!Dino.getDinoAsRect().intersects(Cactus.getCactAsRect()) && !Dino.getDinoAsRect().intersects(Cactus1.getCactAsRect())) {
                    cactusRun();
                    if (score % 100 == 0) {
                        Cactus.speed++;
                        Cactus1.speed++;
                    }
                } else {
                    gameRunning = false;
                    System.out.println("Game over, score was " + score);
                    hs.saveHighScore(score);
                }
            } else if (!Dino.getDinoAsRect().intersects(Cactus.getCactAsRectDark()) && !Dino.getDinoAsRect().intersects(Cactus1.getCactAsRectDark())) {
                cactusRun();
            } else {
                gameRunning = false;
                System.out.println("Game over, score was " + score);
                hs.saveHighScore(score);
            }
        }
    }

    private void cactusRun() {
        if (Cactus.x > -1) {
            cactus.newMove();
            cactus1.newMove();
            if (Cactus.getCactAsRect().intersects(Cactus1.getCactAsRect())) {
                Cactus1.x += 300;
            }
            if (Cactus1.x < -1) {
                Cactus1.x = Main.Width + 300 * (int) (Math.random());
                if (!darkMode) {
                    Cactus1.image = Cactus1.generateImage();
                } else {
                    Cactus1.imageDark = Cactus1.generateImageDark();
                }
            }
        } else {
            Cactus.x = Main.Width + 5 * (int) (Math.random() * 100);
            if (!darkMode) {
                Cactus.image = Cactus.generateImage();
            } else {
                Cactus.imageDark = Cactus.generateImageDark();
            }
        }
        score++;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}