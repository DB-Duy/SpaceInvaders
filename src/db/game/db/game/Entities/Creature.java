package db.game.db.game.Entities;
import db.game.db.game.TextReader.Text;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 3;
    public static final float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 100;

    protected int health;
    protected float speed, xMove, yMove;

    protected Color yellow;

    public Creature(float x, float y, int width, int height) {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

        yellow = new Color(241, 217,27);
    }

    public void move() {
        x += xMove;
        y += yMove;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

}