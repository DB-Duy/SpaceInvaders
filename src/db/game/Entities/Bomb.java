package db.game.Entities;

import db.game.Display.Assets;
import db.game.Input.KeyManager;
import db.game.Main.Handler;
import db.game.TextReader.Text;

import java.awt.*;

public class Bomb extends Creature {

    private Text text;

    public Bomb(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        text = new Text(".//res//text//textfile1.txt");
        do {
            this.word = text.randomWord();
        } while (word.length() > 4);
        this.word = text.randomWord();
    }

    public void tick() {
        y = (int) y + getSpeed();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.bomb, (int) x, (int) y, width - 10, height - 10, null);

        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(font);
        int c = (int) this.x + ((this.width-10) - metrics.stringWidth(word)) / 2;
        g.setFont(font);
        g.drawString(word, c, (int) y + height);
    }
}
