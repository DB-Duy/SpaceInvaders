package db.game.Entities;
import db.game.Main.Game;
import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class Creature extends Entity {

    private Game game;
    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 100;

    protected boolean isBlown;
    protected int health, speed, blownTime, a, b;
    protected String word;
    protected Color yellow;

    protected Font font;


    File fontFile = new File(".//res//font//font.ttf");
    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,fontFile).deriveFont(30f).deriveFont(15f);
            GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.game = game;
        health = DEFAULT_HEALTH;
        speed = 1;
        isBlown = false;
        yellow = new Color(241, 217, 27);
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public boolean getExplosion() {
        return isBlown;
    }

    public void setExplosion() {
        isBlown = true;
    }

    public void explode(int time) {
        setBlownTime(time);
        setExplosion();
        a = (int) this.x;
        b = (int) this.y;
    }

    public void renderExplosion(Graphics g) {
        g.drawImage(Assets.explosion, a, b, width + 20, height + 20, null);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBlownTime() {
        return blownTime;
    }

    public void setBlownTime(int blownTime) {
        this.blownTime = blownTime;
    }

    public String getWord() {
        return word;
    }
}