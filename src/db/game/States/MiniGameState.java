package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Entities.Asteroid;
import db.game.Entities.Player;
import db.game.Main.Handler;
import db.game.Sounds.Sound;
import db.game.UI.ImageButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MiniGameState extends State {

    private Player player;
    private ArrayList<Asteroid> asteroids;
    int time = 99;

    public MiniGameState(Handler handler) {
        super(handler);
        Sound.stopLoop();
        Sound.playSoundLoop(".//res//sounds//minibg.wav");
        asteroids = new ArrayList<>();
        player = new Player(handler, 450,450);
    }

    public void addAsteroid() {



        if (time > 10000) {
            time = 0;
        }
    }

    @Override
    public void tick() {
        time++;
        addAsteroid();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.miniGame, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(ImageLoader.loadImage("/resources/minibg.png"), 0, 0, 1000, 600, null);
        player.render(g);

    }
}
