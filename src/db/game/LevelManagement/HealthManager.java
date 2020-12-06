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

    public void render(Graphics g, CollisionDetection detection) {
        for (int i = 0; i < 3; i++) {
            if (detection.getCollision() <= i) {
                g.drawImage(Assets.emptyHeart,20 + i*60,7,50,50,null);
            }
            else {
                g.drawImage(Assets.fullHeart,20 + i*60,7,50,50,null);
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
