package db.game.Display;

import java.util.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage emptyHeart, fullHeart, monster1, monster2, monster3, monster4, asteroid, miniGame,
                                rocket, explosion, shield, startScreen, bomb, gameOverScreen, stars, title, blackBar, mountains,
                                selectScreen, enterScreen, leaderboardScreen, settingScreen;
    public static ArrayList<BufferedImage> levelBars,
                                           startButtons, quitButtons, instructions, settings, tryAgain, next, back,
                                           instructionScreens, explosions, selectButtons, createButtons, arrowButtons,
                                           leaderboardButtons, volumeBars, arrowButtons2, gameOverFlash;
    private static SpriteSheet sheet1, sheet2, sheet3,
                               startChoices, quitChoices, instructionChoices, settingChoices, tryAgainChoices, backChoices, nextChoices,
                               instructionScreen, sheet4, selectChoices, createChoices, arrowChoices, leaderboardChoices,
                               volumeSheet, arrowChoices2, gameOver;
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
        explosions = new ArrayList<>();
        selectButtons = new ArrayList<>();
        createButtons = new ArrayList<>();
        arrowButtons = new ArrayList<>();
        leaderboardButtons = new ArrayList<>();
        volumeBars = new ArrayList<>();
        arrowButtons2 = new ArrayList<>();
        gameOverFlash = new ArrayList<>();


        sheet1 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet1.png"));
        sheet2 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet2.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/resources/sheet4.png"));
        sheet4 = new SpriteSheet(ImageLoader.loadImage("/resources/explosion.png"));
        startChoices = new SpriteSheet(ImageLoader.loadImage("/resources/startchoices.png"));
        quitChoices = new SpriteSheet(ImageLoader.loadImage("/resources/quitchoices.png"));
        instructionChoices = new SpriteSheet(ImageLoader.loadImage("/resources/instructions.png"));
        settingChoices = new SpriteSheet(ImageLoader.loadImage("/resources/settings.png"));
        tryAgainChoices = new SpriteSheet(ImageLoader.loadImage("/resources/tryagain.png"));
        backChoices = new SpriteSheet(ImageLoader.loadImage("/resources/backchoices.png"));
        nextChoices = new SpriteSheet(ImageLoader.loadImage("/resources/nextchoices.png"));
        instructionScreen = new SpriteSheet(ImageLoader.loadImage("/resources/instructionscreen.png"));
        selectChoices = new SpriteSheet(ImageLoader.loadImage("/resources/selectplayer.png"));
        createChoices = new SpriteSheet(ImageLoader.loadImage("/resources/createnew.png"));
        arrowChoices = new SpriteSheet(ImageLoader.loadImage("/resources/arrows.png"));
        leaderboardChoices = new SpriteSheet(ImageLoader.loadImage("/resources/leaderboardchoices.png"));
        volumeSheet = new SpriteSheet(ImageLoader.loadImage("/resources/volume.png"));
        arrowChoices2 = new SpriteSheet(ImageLoader.loadImage("/resources/arrows2.png"));
        gameOver = new SpriteSheet(ImageLoader.loadImage("/resources/gameoverflash.png"));


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
        blackBar = ImageLoader.loadImage("/resources/blackbar.png");
        mountains = ImageLoader.loadImage("/resources/mountains.png");
        asteroid = ImageLoader.loadImage("/resources/asteroid.png");
        miniGame = ImageLoader.loadImage("/resources/minibgnew.png");
        selectScreen = ImageLoader.loadImage("/resources/selectplayerscreen.png");
        enterScreen = ImageLoader.loadImage("/resources/entername.png");
        leaderboardScreen = ImageLoader.loadImage("/resources/leaderboard.png");
        settingScreen = ImageLoader.loadImage("/resources/settingscreen.png");

        for (int i = 0; i < 2; i++) {
            gameOverFlash.add(gameOver.crop(0, i * 150, 801, 149));
        }


        for (int i = 0; i < 11; i++) {
            levelBars.add(sheet3.crop(0, 84 * i, 239, 83));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                volumeBars.add(volumeSheet.crop(j * 400, i * 100, 399, 99));
            }
        }



        for (int i = 0; i < 2; i++) {
            startButtons.add(startChoices.crop(i * 200, 0, 199, 29));
            quitButtons.add(quitChoices.crop(i * 200, 0, 199, 29));
            instructions.add(instructionChoices.crop(i * 200, 0, 199, 29));
            settings.add(settingChoices.crop(i * 200, 0, 199, 29));
            next.add(nextChoices.crop(i * 200, 0, 199, 29));
            back.add(backChoices.crop(i * 200, 0, 199, 29));
            tryAgain.add(tryAgainChoices.crop(i * 200, 0, 199, 29));
            selectButtons.add(selectChoices.crop(i * 200, 0, 199, 29));
            createButtons.add(createChoices.crop(i * 235, 0, 234, 29));
            arrowButtons.add(arrowChoices.crop(i * 30, 0, 29, 29));
            leaderboardButtons.add(leaderboardChoices.crop(i * 230, 0, 229, 29));
            arrowButtons2.add(arrowChoices2.crop(i * 30, 0, 29, 29));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                instructionScreens.add(instructionScreen.crop(j * 1000, i * 600, 999, 599));
            }
        }

        for (int i = 0; i < 6; i++) {
            explosions.add(sheet4.crop(i * 250, 0, 249, 249));
        }

        /*for (int i = 0; i < 8; i++) {
            explosions.add(sheet4.crop(i*250, 0, 249, 249));
        }*/



    }

}
