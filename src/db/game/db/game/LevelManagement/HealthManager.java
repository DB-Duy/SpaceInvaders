package db.game.db.game.LevelManagement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import db.game.db.game.Display.Assets;
import db.game.db.game.Game;

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
                g.drawImage(Assets.emptyHeart,20 + i*60,24,60,60,null);
            }
            else {
                g.drawImage(Assets.fullHeart,20 + i*60,24,60,60,null);
            }
        }

    }
}
