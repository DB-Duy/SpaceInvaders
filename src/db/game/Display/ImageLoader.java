package db.game.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage cropImage(BufferedImage image, int x, int y, int width, int height) {
        BufferedImage cropped = image.getSubimage(x, y, width, height);
        return cropped;
    }
}
