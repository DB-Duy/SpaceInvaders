package db.game.Entities;

import db.game.Main.Game;
import db.game.Main.Handler;
import db.game.TextReader.Text;
import db.game.Display.Assets;
import db.game.Input.KeyManager;
import db.game.LevelManagement.LevelManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Monster extends Creature {

    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text;
    private KeyManager input;
    private int i;
    private LevelManager level;


    public Monster(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

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