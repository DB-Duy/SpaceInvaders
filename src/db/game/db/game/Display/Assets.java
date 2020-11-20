package db.game.db.game.Display;


import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, rocket, star;
    private static SpriteSheet sheet;
    private static int size = 249;

    public static void init() {

        sheet = new SpriteSheet(ImageLoader.loadImage("/resources/sheet3.png"));


        emptyHeart = sheet.crop(0,0, size, size);
        //fullHeart = ImageLoader.load("/resources/texture2/heart_full2.png");
        //monster1 = ImageLoader.load("/resources/texture2/monster32.png");
        monster2 = sheet.crop(size + 1,size + 1, size, size);
        monster3 = sheet.crop(0,  size + 1, size, size);
        rocket = sheet.crop(size+1, 2*(size+1), size, size);
        //star = sheet.crop(6*(size + 1), 0, size, size);

    }

}
