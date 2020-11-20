package db.game.db.game.Entities;

import db.game.db.game.Game;
import db.game.db.game.Display.Assets;

import java.awt.*;


public class Player extends Creature {

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        this.game = game;
    }

    public void tick() {
        /*getInput();
        move();*/
    }

    /*public void getInput() {
        xMove = 0;
        yMove = 0;

        if (game.getKeyManager().up) {
            yMove = - speed;
        }
        else if (game.getKeyManager().down) {
            yMove = speed;
        }
        else if (game.getKeyManager().left) {
            xMove = - speed;
        }
        else if (game.getKeyManager().right) {
            xMove = speed;
        }

    }*/

    public void render(Graphics g) {
        g.drawImage(Assets.rocket, (int) x, (int) y, width, height, null);
        g.setColor(Color.cyan);
        g.setFont(new Font("TimesNewRoman",1,20));
        g.drawString(game.getKeyManager().getWordTyped(),(int) x,(int) y + 95);
    }



}