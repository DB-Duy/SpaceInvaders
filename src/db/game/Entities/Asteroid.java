package db.game.Entities;

import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;

public class Asteroid extends Creature {

    private int a = 0, b = 0;

    public Asteroid(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.word = String.valueOf(a) + " + " + String.valueOf(b);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void tick() {
        y++;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.asteroid, (int) x, (int) y, width + 10, height + 10, null);

        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(font);
        int c = (int) this.x + ((this.width - 10) - metrics.stringWidth(word)) / 2;
        g.setFont(font);
        g.drawString(word, c, (int) y + height);
    }

}
