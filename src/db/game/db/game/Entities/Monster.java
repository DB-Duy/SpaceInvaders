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
    private String word;
    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text = new Text();
    private KeyManager input = new KeyManager();
    private int i = -1;
    private boolean isBlown = false;
    private int blownTime;
    private int a , b;
    private int speed;
    private LevelManager level;


    public Monster(Game game, float x, float y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        monsters = new ArrayList<>();
        this.game = game;
        level = new LevelManager();
        monsters.add(Assets.monster1);
        monsters.add(Assets.monster2);
        monsters.add(Assets.monster3);
        this.word = text.randomWord();
    }

    public String getWord(){
        return word;
    }

    public void tick() {
        double angle = Math.tan(650/(250-x));
        angle = Math.toRadians(angle);
        y = (int) y + speed;
        if (Math.cos(angle) != 0 && x < 160 && y % 6 == 0) {
            x += Math.cos(angle) * speed;
        }
        else if (Math.cos(angle) != 0 && x > 250 && y % 6 == 0) {
            x -= Math.cos(angle) * speed;
        }
    }

    public void render(Graphics g) {
        g.drawImage(monsters.get(i), (int) x, (int) y, width - 10, height - 10, null);
        g.setColor(yellow);
        g.setFont(new Font("TimesNewRoman",Font.PLAIN,20));
        g.drawString(word,(int) x,(int) y + 95);
    }

    public void setTexture(int i) {
        this.i = i;
    }

    public int getTexture() {
        return this.i;
    }

    public void renderExplosion(Graphics g) {
        g.drawImage(Assets.explosion, a, b, width + 20, height + 20, null);
    }

    public void explode(int time) {
        this.blownTime = time;
        this.isBlown = true;
        a = (int) this.x;
        b = (int) this.y;
    }

    public int getTimeBlown() {
        return blownTime;
    }

    public boolean getExplosion() {
        return isBlown;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}