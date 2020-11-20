package db.game.db.game.entities;

import db.game.db.game.display.Assets;
import db.game.db.game.textreader.ReadFile;

import java.awt.*;

public class Player extends Entity{

    public Player(float x, float y) {
        super(x, y);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.ship1,(int)x,(int)y,null);

    }
}
