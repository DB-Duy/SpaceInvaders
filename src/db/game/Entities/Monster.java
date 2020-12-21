package db.game.Entities;

import db.game.FunctionManagement.ScoreManager;
import db.game.Main.Game;
import db.game.Main.Handler;
import db.game.TextReader.Text;
import db.game.Display.Assets;
import db.game.Input.KeyManager;
import db.game.FunctionManagement.LevelManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Monster extends Creature {

    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text;


    public Monster(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        monsters = new ArrayList<>();
        text = new Text(".//res//text//textfile1.txt");
        monsters.add(Assets.monster1);
        monsters.add(Assets.monster2);
        monsters.add(Assets.monster3);
        monsters.add(Assets.monster4);

        this.word = text.randomWord();
    }


    public void tick() {
        if (!getExplosion()) {
            y = (int) y + getSpeed();
        }
    }

    public void render(Graphics g) {
        g.drawImage(monsters.get(i), (int) x, (int) y, width - 10, height - 10, null);

        g.setColor(yellow);
        FontMetrics metrics = g.getFontMetrics(font);
        int c = (int) this.x + ((this.width - 10) - metrics.stringWidth(word)) / 2;
        g.setFont(font);
        g.drawString(word, c, (int) y + height);
    }


    public void scoring(int numKilled, ScoreManager score) {
        if (numKilled > 0) {
            if (numKilled < 5) {
                score.individual();
            }
            else if (numKilled < 10) {
                score.combo_1();
            }
            else if (numKilled < 20) {
                score.combo_2();
            }
            else if (numKilled >= 20) {
                score.combo_3();
            }
        }
    }

}