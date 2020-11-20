package db.game.db.game.Entities;

import db.game.db.game.Game;
import db.game.db.game.Display.Assets;
import db.game.db.game.TextReader.Text;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Monster extends Creature {
    private String word;
    private Game game;
    private ArrayList<BufferedImage> monsters;
    private Text text=new Text();


    public Monster(Game game, float x, float y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        monsters = new ArrayList<>();
        this.game = game;

        monsters.add(Assets.monster1);
        monsters.add(Assets.monster2);
        monsters.add(Assets.monster3);
        this.word=text.randomWord();
    }

    public void tick() {
        y = (int) y + 1;
    }

    public void render(Graphics g, int i) {
        g.drawImage(monsters.get(i), (int) x, (int) y, width - 10, height - 10, null);
        g.setColor(Color.cyan);
        g.setFont(new Font("TimesNewRoman",1,20));
        g.drawString(word,(int)x,(int)y+95);
        //g.drawImage(Assets.monster1, (int) x + 100, (int) y, width, height, null);
    }


}