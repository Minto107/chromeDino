package release.things;

import release.Main;
import release.res.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus {
    public static final int finalY = 298;
    public static int x;
    public static int y;
    public static int speed;
    public static BufferedImage image, imageDark;

    public Cactus() {
        x = Main.Width + 10;
        y = 298;
        speed = 18;
        image = generateImage();
        imageDark = generateImageDark();
    }

    public static BufferedImage generateImage() {
        int random = (int) (Math.random() * 5) + 1;
        switch (random) {
            case 1:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\images\\Cactus-1.png");
            case 2:
                Cactus.y = finalY + 15;
                return new LoadImage().getImage(".\\src\\release\\images\\Cactus-2.png");
            case 3:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\images\\Cactus-3.png");
            case 4:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\images\\Cactus-4.png");
            case 5:
                Cactus.y = finalY + 15;
                return new LoadImage().getImage(".\\src\\release\\images\\Cactus-5.png");
            default:
                return null;
        }
    }

    public static BufferedImage generateImageDark() {
        int random = (int) (Math.random() * 5) + 1;
        switch (random) {
            case 1:
            case 2:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\imagesDark\\Cactus-1.png");
            case 3:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\imagesDark\\Cactus-2.png");
            case 4:
            case 5:
                Cactus.y = finalY;
                return new LoadImage().getImage(".\\src\\release\\imagesDark\\Cactus-3.png");
            default:
                return null;
        }
    }

    public static Rectangle getCactAsRect() {
        Rectangle cactus = new Rectangle();
        cactus.x = x;
        cactus.y = y;
        cactus.width = image.getWidth();
        cactus.height = image.getHeight();
        return cactus;
    }

    public static Rectangle getCactAsRectDark() {
        Rectangle cactus = new Rectangle();
        cactus.x = x;
        cactus.y = y;
        cactus.width = imageDark.getWidth();
        cactus.height = imageDark.getHeight();
        return cactus;
    }

    public void create(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void createDark(Graphics g) {
        g.drawImage(imageDark, x, y, null);
    }

    public void newMove() {
        x -= speed;
    }
}
