package db.game.db.game.Entities;

import db.game.db.game.Game;
import db.game.db.game.Display.Assets;
import db.game.db.game.Input.KeyManager;
import db.game.db.game.LevelManagement.LevelManager;
import db.game.db.game.TextReader.Text;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.ArrayList;
import java.util.logging.Level;

public class Monster extends Creature {

    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text;
    private KeyManager input;
    private int i;
    private LevelManager level;


    public Monster(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        i = -1;
        monsters = new ArrayList<>();
        text = new Text();
        input = new KeyManager();
        level = new LevelManager();
        monsters.add(Assets.monster1);
        monsters.add(Assets.monster2);
        monsters.add(Assets.monster3);
        monsters.add(Assets.monster4);
        this.word = text.randomWord();
    }


    public void tick() {
        if (!getExplosion()) {
            y = (int) y + getSpeed();
        }
    }

    public void render(Graphics g) {
        g.drawImage(monsters.get(i), (int) x, (int) y, width - 10, height - 10, null);
        g.setColor(yellow);

        FontMetrics metrics = g.getFontMetrics(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        int c = (int) this.x + ((this.width-10) - metrics.stringWidth(word)) / 2;
        g.setFont(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        g.drawString(word, c, (int) y + height);
    }

    public void setTexture(int i) {
        this.i = i;
    }

    public int getTexture() {
        return this.i;
    }



}