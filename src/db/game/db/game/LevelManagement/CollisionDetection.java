package db.game.db.game.LevelManagement;

import db.game.db.game.Entities.*;

public class CollisionDetection {

    private int collision;

    public CollisionDetection() {

    }

    public boolean hasCollided(Monster monster) {
        if (monster.getY() == 560 && monster.getX() >= 110 && monster.getX() <= 300) {
            collision++;
            System.out.println("Collided");
            return true;
        }
        return false;
    }

    public int getCollision() {
        return collision;
    }
}
