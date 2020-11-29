package release.things;

import release.res.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackG {
    final BufferedImage image;
    final BufferedImage imageDark;
    private final int x = 0;
    private final int y = 341;

    public BackG() {
        image = new LoadImage().getImage(".\\src\\release\\images\\Ground.png");
        imageDark = new LoadImage().getImage(".\\src\\release\\imagesDark\\Ground.png");
    }

    public void create(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void createDark(Graphics g) {
        g.drawImage(imageDark, x, y, null);
    }
}
