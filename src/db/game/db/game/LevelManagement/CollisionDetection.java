package db.game.db.game.LevelManagement;

import db.game.db.game.Entities.*;

public class CollisionDetection {

    private int collision = 3;

    public CollisionDetection() {

    }

    public boolean hasCollided(Creature creature) {
        if ((creature.getY() >= 360 && creature.getX() >= 360 && creature.getX() <= 550) || creature.getY() >= 510) {
            if(!creature.getExplosion()) {
                collision--;
                return true;
            }
        }
        return false;
    }

    public void setCollision(int collision) {
        this.collision = collision;
    }

    public int getCollision() {
        return collision;
    }
}
