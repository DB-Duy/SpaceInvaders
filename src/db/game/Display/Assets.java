package db.game.Display;

import java.util.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, monster4, rocket, explosion, shield, startScreen;
    public static ArrayList<BufferedImage> levelBars, startButtons, quitButtons;
    private static SpriteSheet sheet1, sheet2, sheet3, startChoices, quitChoices;
    private static int size = 249;


    public static void init() {
        levelBars = new ArrayList<>();
        startButtons = new ArrayList<>();
        quitButtons = new ArrayList<>();

        sheet1 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet1.png"));
        sheet2 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet2.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet4.png"));
        startChoices = new SpriteSheet(ImageLoader.loadImage("/resources/startchoices.png"));
        quitChoices = new SpriteSheet(ImageLoader.loadImage("/resources/quitchoices.png"));

        emptyHeart = sheet1.crop(0,0, size, size);
        fullHeart = sheet2.crop(2*(size + 1), 0, size, size);
        monster1 = sheet2.crop(4*(size + 1), 0, size, size);
        monster2 = sheet1.crop(size + 1,size + 1, size, size);
        monster3 = sheet2.crop(5*(size + 1),  0, size, size);
        rocket = sheet1.crop(size+1, 2*(size+1), size, size);
        explosion = sheet2.crop(0, 0, size, size);
        monster4 = sheet2.crop(3*(size + 1), 0, size, size);
        shield = ImageLoader.loadImage("/resources/shield.png");
        startScreen = ImageLoader.loadImage("/resources/startscreen.png");

        for (int i = 0; i < 11; i++) {
            levelBars.add(sheet3.crop(0, 84 * i, 239, 83));
        }

        for (int i = 0; i < 2; i++) {
            startButtons.add(startChoices.crop(i * 200, 0, 199, 29));
            quitButtons.add(quitChoices.crop(i * 200, 0, 199, 29));
        }


    }

}
