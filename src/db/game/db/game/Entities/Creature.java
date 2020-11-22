package db.game.db.game.Entities;
import db.game.db.game.TextReader.Text;

import java.io.File;
import java.io.InputStream;
import java.awt.*;
import java.awt.Font;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 100;

    protected int health;
    protected float speed, xMove, yMove;

    protected Color yellow;

    public Creature(float x, float y, int width, int height) {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        xMove = 0;
        yMove = 0;

        yellow = new Color(241, 217, 27);
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }



}