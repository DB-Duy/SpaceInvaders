package db.game.Display;

import java.util.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, monster4,
                                rocket, explosion, shield, startScreen, bomb, gameOverScreen, stars, title;
    public static ArrayList<BufferedImage> levelBars,
                                           startButtons, quitButtons, instructions, settings, tryAgain, next, back,
                                           instructionScreens;
    private static SpriteSheet sheet1, sheet2, sheet3,
                               startChoices, quitChoices, instructionChoices, settingChoices, tryAgainChoices, backChoices, nextChoices,
                               instructionScreen;
    private static int size = 249;


    public static void init() {
        levelBars = new ArrayList<>();
        startButtons = new ArrayList<>();
        quitButtons = new ArrayList<>();
        instructions = new ArrayList<>();
        settings = new ArrayList<>();
        tryAgain = new ArrayList<>();
        back = new ArrayList<>();
        next = new ArrayList<>();
        instructionScreens = new ArrayList<>();


        sheet1 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet1.png"));
        sheet2 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet2.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet4.png"));
        startChoices = new SpriteSheet(ImageLoader.loadImage("/resources/startchoices.png"));
        quitChoices = new SpriteSheet(ImageLoader.loadImage("/resources/quitchoices.png"));
        instructionChoices = new SpriteSheet(ImageLoader.loadImage("/resources/instructions.png"));
        settingChoices = new SpriteSheet(ImageLoader.loadImage("/resources/settings.png"));
        tryAgainChoices = new SpriteSheet(ImageLoader.loadImage("/resources/tryagain.png"));
        backChoices = new SpriteSheet(ImageLoader.loadImage("/resources/backchoices.png"));
        nextChoices = new SpriteSheet(ImageLoader.loadImage("/resources/nextchoices.png"));
        instructionScreen = new SpriteSheet(ImageLoader.loadImage("/resources/instructionscreen.png"));


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
        bomb = ImageLoader.loadImage("/resources/bomb.png");
        gameOverScreen = ImageLoader.loadImage("/resources/gameover.png");
        stars = ImageLoader.loadImage("/resources/stars.png");
        title = ImageLoader.loadImage("/resources/title.png");


        for (int i = 0; i < 11; i++) {
            levelBars.add(sheet3.crop(0, 84 * i, 239, 83));
        }

        for (int i = 0; i < 2; i++) {
            startButtons.add(startChoices.crop(i * 200, 0, 199, 29));
            quitButtons.add(quitChoices.crop(i * 200, 0, 199, 29));
            instructions.add(instructionChoices.crop(i * 200, 0, 199, 29));
            settings.add(settingChoices.crop(i * 200, 0, 199, 29));
            next.add(nextChoices.crop(i * 200, 0, 199, 29));
            back.add(backChoices.crop(i * 200, 0, 199, 29));
            tryAgain.add(tryAgainChoices.crop(i * 200, 0, 199, 29));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                instructionScreens.add(instructionScreen.crop(j * 1000, i * 600, 999, 599));
            }
        }



    }

}
