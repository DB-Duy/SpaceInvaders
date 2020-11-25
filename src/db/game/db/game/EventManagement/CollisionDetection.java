package db.game.db.game.EventManagement;

import db.game.db.game.Entities.*;

public class CollisionDetection {

    private int collision = 3;

    public CollisionDetection() {

    }

    public boolean hasCollided(Monster monster) {
        if (monster.getY() == 560 && monster.getX() >= 110 && monster.getX() <= 300) {
            collision--;
            return true;
        }
        return false;
    }

    public int getCollision() {
        return collision;
    }
}
