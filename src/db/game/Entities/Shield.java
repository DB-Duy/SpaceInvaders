package db.game.Entities;
import db.game.Main.Game;
import db.game.Main.Handler;
import db.game.TextReader.Text;
import db.game.Display.Assets;

import java.awt.*;

public class Shield extends Creature {

    private Text text;

    public Shield(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        text = new Text(".//res//text//textfile2.txt");
        word = text.randomWord();
    }

    public void tick() {
        y = (int) y + getSpeed();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.shield, (int) x, (int) y, width - 20, height - 20, null);
        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(font);
        int c = (int) this.x + ((this.width - 20) - metrics.stringWidth(word)) / 2;
        g.setFont(font);
        g.drawString(word, c, (int) y + height);
    }


}
