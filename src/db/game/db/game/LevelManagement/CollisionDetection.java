package db.game.db.game.LevelManagement;

import db.game.db.game.Entities.*;

public class CollisionDetection {

    private int collision = 3;

    public CollisionDetection() {

    }

    public boolean hasCollided(Monster monster) {
        if ((monster.getY() >= 360 && monster.getX() >= 360 && monster.getX() <= 550) || monster.getY() >= 510) {
            if(!monster.getExplosion()) {
                collision--;
                return true;
            }
        }
        return false;
    }

    public int getCollision() {
        return collision;
    }
}
