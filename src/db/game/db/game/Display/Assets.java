package db.game.db.game.Display;


import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, rocket, explosion;
    private static SpriteSheet sheet1, sheet2;
    private static int size = 249;

    public static void init() {

        sheet1 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet1.png"));
        sheet2 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet2.png"));

        emptyHeart = sheet1.crop(0,0, size, size);
        fullHeart = sheet2.crop(0, size + 1, size, size);
        monster1 = sheet2.crop(size + 1, size + 1, size, size);
        monster2 = sheet1.crop(size + 1,size + 1, size, size);
        monster3 = sheet2.crop(0,  2*(size + 1), size, size);
        rocket = sheet1.crop(size+1, 2*(size+1), size, size);
        explosion = sheet2.crop(0, 0, size, size);

    }

}
