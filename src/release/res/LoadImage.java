package release.res;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class LoadImage {

    public BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
