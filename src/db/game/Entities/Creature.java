package db.game.Entities;
import db.game.Main.Game;
import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.Sounds.Sound;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class Creature {

    protected Handler handler;
    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 100;

    protected float x, y;
    protected int width, height;
    public int i = -1;

    protected boolean isBlown;
    protected int health, speed, blownTime, a, b;
    protected String word;
    protected Color yellow;

    protected Font font;


    File fontFile = new File(".//res//font/gotham.ttf");
    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,fontFile).deriveFont(19f);
            GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Creature(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x; this.y = y;
        this.width = width; this.height = height;
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
        Sound.playSound(".//res//Sounds//die.wav");
        isBlown = true;
    }

    public void explode(int time) {
        setBlownTime(time);
        setExplosion();
        a = (int) this.x;
        b = (int) this.y;
    }

    public void renderExplosion(Graphics g, int time) {
        int i = Math.abs(this.getBlownTime() - time)/6;
        //System.out.println(i + " drawn");
        g.drawImage(Assets.explosions.get(i), (int) x, (int) y, width + 60, height + 60,null);
    }

    public int getTexture() {
        return i;
    }

    public void setTexture(int i) {
        this.i = i;
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}