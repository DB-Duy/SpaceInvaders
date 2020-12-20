package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MiniGameState extends State {

    private ArrayList<BufferedImage> start;

    public MiniGameState(Handler handler) {
        super(handler);


        start = Assets.startButtons;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.miniGame, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        //g.drawImage(ImageLoader.loadImage("/resources/bg.png"), 0, 0, 1000, 600, null);
    }
}
