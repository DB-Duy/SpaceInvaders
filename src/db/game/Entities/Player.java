package db.game.Entities;

import db.game.Main.Game;
import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;


public class Player extends Creature {


    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }


    public void tick() {

    }


    public void render(Graphics g) {

        g.drawImage(Assets.rocket, (int) x, (int) y, width, height, null);
        g.setColor(yellow);

        FontMetrics metrics = g.getFontMetrics(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        int c = (int) this.x + (this.width - metrics.stringWidth(handler.getKeyManager().getWordTyped())) / 2;

        g.setFont(new Font("VCR_OSD_MONO_1_001",Font.PLAIN,20));
        g.drawString(handler.getKeyManager().getWordTyped(),c ,(int) y + 110);
    }



}