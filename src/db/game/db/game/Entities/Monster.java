package db.game.db.game.Entities;

import db.game.db.game.Game;
import db.game.db.game.Display.Assets;
import db.game.db.game.Input.KeyManager;
import db.game.db.game.TextReader.Text;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.ArrayList;

public class Monster extends Creature {
    private String word;
    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text = new Text();
    private KeyManager input = new KeyManager();
    private int i = -1;
    private boolean isBlown = false;
    private int blownTime;
    private int a , b; //current position (so that the explosion doesn't move)


    public Monster(Game game, float x, float y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        monsters = new ArrayList<>();
        this.game = game;

        monsters.add(Assets.monster1);
        monsters.add(Assets.monster2);
        monsters.add(Assets.monster3);
        this.word = text.randomWord();
    }

    public String getWord(){
        return word;
    }

    public void tick() {;
        double angle = Math.tan(650/(250-x));
        angle = Math.toRadians(angle);
        y = (int) y + 1;
        if (Math.cos(angle) != 0 && x < 160 && y % 6 == 0) {
            x += Math.cos(angle);
        }
        else if (Math.cos(angle) != 0 && x > 250 && y % 6 == 0) {
            x -= Math.cos(angle);
        }
    }

    public void render(Graphics g) {
        g.drawImage(monsters.get(i), (int) x, (int) y, width - 10, height - 10, null);
        g.setColor(yellow);
        g.setFont(new Font("TimesNewRoman",1,17));
        g.drawString(word,(int) x,(int) y + 95);
    }

    public void setTexture(int i) {
        this.i = i;
    }

    public int getTexture() {
        return this.i;
    }

    public void renderExplode(Graphics g) {
        g.drawImage(Assets.explosion, a, b,width,height,null);
    }

    public void explode(int timeBlown) {
        this.blownTime = timeBlown;
        this.isBlown = true;
        a = (int) this.x;
        b = (int) this.y;
    }
    public int getTimeBlown() {
        return blownTime;
    }

    public boolean getExploded() {
        return isBlown;
    }

}