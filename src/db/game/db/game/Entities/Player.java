package db.game.db.game.Entities;

import db.game.db.game.Game;
import db.game.db.game.Display.Assets;

import java.awt.*;


public class Player extends Creature {

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        this.game = game;
    }


    public void tick() {

    }



    public void render(Graphics g) {

        g.drawImage(Assets.rocket, (int) x, (int) y, width, height, null);
        g.setColor(yellow);

        FontMetrics metrics = g.getFontMetrics(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        int c = (int) this.x + (this.width - metrics.stringWidth(game.getKeyManager().getWordTyped())) / 2;

        g.setFont(new Font("VCR_OSD_MONO_1_001",Font.PLAIN,20));
        g.drawString(game.getKeyManager().getWordTyped(),c ,(int) y + 110);
    }



}