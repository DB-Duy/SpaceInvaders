package db.game.FunctionManagement;

import db.game.Entities.Creature;
import db.game.Entities.Player;

public class CollisionDetection {

    public CollisionDetection() {

    }

    public boolean hasCollided(Creature creature) {
        if ((creature.getY() >= 360 && creature.getX() >= 360 && creature.getX() <= 550) || creature.getY() >= 510) {
            if(!creature.getExplosion()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollided2(Creature creature, Player player) {
        if (creature.getY() + creature.getHeight() >= (player.getHeight() - 20) && creature.getX() >= (player.getX() - 5) && creature.getX() <= (player.getX() + player.getWidth())) {
            if (!creature.getExplosion()) {
                return true;
            }
        }
        return false;
    }
}
