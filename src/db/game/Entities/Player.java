package db.game.Entities;

import db.game.Input.MouseManager;
import db.game.Main.Game;
import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;


public class Player extends Creature {

    private BufferedImage image;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        image = Assets.rocket;
    }


    public void tick() { }



    public void render(Graphics g) {

        g.drawImage(image, (int) x, (int) y, width, height, null);

        g.setColor(yellow);

        FontMetrics metrics = g.getFontMetrics(font.deriveFont(22f));
        int c = (int) this.x + (this.width - metrics.stringWidth(handler.getKeyManager().getWordTyped())) / 2;

        g.setFont(font.deriveFont(22f));
        g.drawString(handler.getKeyManager().getWordTyped(),c ,(int) y + 110);
    }


}