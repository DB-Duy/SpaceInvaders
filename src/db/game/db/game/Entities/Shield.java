package db.game.db.game.Entities;
import db.game.db.game.Display.Assets;
import db.game.db.game.Game;
import db.game.db.game.TextReader.Text;

import java.awt.*;

public class Shield extends Creature {

    private Text text;

    public Shield(Game game, float x, float y) {
        super(game, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        text = new Text();

        do {
            this.word = text.randomWord();
        } while (word.length() <= 7);
    }

    public void tick() {
        y = (int) y + getSpeed();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.shield, (int) x, (int) y, width - 20, height - 20, null);
        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        int c = (int) this.x + ((this.width - 20) - metrics.stringWidth(word)) / 2;
        g.setFont(new Font("VCR_OSD_MONO_1_001",Font.PLAIN, 20));
        g.drawString(word, c, (int) y + height);
    }


}
