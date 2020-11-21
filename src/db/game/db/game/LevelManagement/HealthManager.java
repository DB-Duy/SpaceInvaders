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
        if (detection.getCollision() == 0) {
            for (int i = 0; i < 3; i++) {
                g.drawImage(Assets.fullHeart, 30 + i*60,20, 50, 50, null);
            }
        }
        else if (detection.getCollision() == 1) {
            g.drawImage(Assets.emptyHeart, 30,20, 50, 50, null);
            for (int i = 0; i < 2; i++) {
                g.drawImage(Assets.fullHeart, 30 + (i+1)*60,20, 50, 50, null);
            }
        }
        else if (detection.getCollision() == 2) {
            for (int i = 0; i < 2; i++) {
                g.drawImage(Assets.emptyHeart, 30 + i *60,20, 50, 50, null);
            }
            g.drawImage(Assets.fullHeart, 30 + 2*60,20, 50, 50, null);
        }
        else {
            for (int i = 0; i < 3; i++) {
                g.drawImage(Assets.emptyHeart, 30 + i * 60, 20, 50, 50, null);
            }
        }

    }
}
