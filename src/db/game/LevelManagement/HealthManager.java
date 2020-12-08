package db.game.LevelManagement;

import java.awt.*;

import db.game.Main.Game;
import db.game.Display.Assets;

public class HealthManager {

    private int health;
    private Game game;

    public HealthManager(int health) {
        this.health = health;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int i = 0; i < health; i++) {
            g.drawImage(Assets.fullHeart, 20 + i*60, 7, 50, 50, null);
        }
        for (int j = health; j < 3; j++) {
            g.drawImage(Assets.emptyHeart, 20 + j*60, 7, 50, 50, null);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
