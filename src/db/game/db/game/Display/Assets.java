package db.game.db.game.Display;

import java.util.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, rocket, explosion;
    public static ArrayList<BufferedImage> levelBars;
    private static SpriteSheet sheet1, sheet2, sheet3;
    private static int size = 249;


    public static void init() {
        levelBars = new ArrayList<>();

        sheet1 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet1.png"));
        sheet2 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet2.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet3.png"));

        emptyHeart = sheet1.crop(0,0, size, size);
        fullHeart = sheet2.crop(0, size + 1, size, size);
        monster1 = sheet2.crop(size + 1, size + 1, size, size);
        monster2 = sheet1.crop(size + 1,size + 1, size, size);
        monster3 = sheet2.crop(0,  2*(size + 1), size, size);
        rocket = sheet1.crop(size+1, 2*(size+1), size, size);
        explosion = sheet2.crop(0, 0, size, size);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                levelBars.add(sheet3.crop(j * (size + 1), i * (size + 1), size, size));
            }
        }

    }

}
