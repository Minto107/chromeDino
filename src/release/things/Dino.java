package release.things;

import release.res.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dino {
    static int x, y, jumpMax, gravity, vy;
    static BufferedImage image, imageDark;

    public Dino() {
        x = 100;
        y = 300;
        jumpMax = 200;
        gravity = 10;
        vy = 15;
        image = new LoadImage().getImage(".\\src\\release\\images\\Dino.png");
        imageDark = new LoadImage().getImage(".\\src\\release\\imagesDark\\Dino.png");
    }

    public static Rectangle getDinoAsRect() {
        Rectangle dino = new Rectangle();
        dino.x = x;
        dino.y = y;
        dino.width = image.getWidth();
        dino.height = image.getHeight();
        return dino;
    }

    public void jump() {
        new Thread((new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 7; i++) {
                    if (y >= jumpMax) {
                        y -= vy;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                gravity();
            }
        })).start();

    }

    @SuppressWarnings("BusyWait")
    public void gravity() {
        while (y < 300) {
            try {
                Thread.sleep(50);
                y += gravity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (y > 300) {
            y = 300;
        }

    }

    public void create(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void createDark(Graphics g) {
        g.drawImage(imageDark, x, y, null);
    }
}
