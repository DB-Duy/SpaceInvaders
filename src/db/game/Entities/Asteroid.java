package db.game.Entities;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.TextReader.Text;

import java.awt.*;

public class Asteroid extends Creature {

    private Text text;

    public Asteroid(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        text = new Text(".//res//text//textfile2.txt");
        word = text.randomWord();
    }

    public void tick() {
        y = (int) y + getSpeed();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.asteroid, (int) x, (int) y, width + 10, height + 10, null);

        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(font);
        int c = (int) this.x + ((this.width + 10) - metrics.stringWidth(word)) / 2;
        g.setFont(font);
        g.drawString(word, c, (int) y + height + 30);
    }

}
