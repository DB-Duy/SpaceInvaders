package db.game.db.game.states;

import db.game.db.game.Entities.Monster;
import db.game.db.game.Entities.Player;
import db.game.db.game.Game;
import db.game.db.game.LevelManagement.*;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;
    private CollisionDetection detection;
    private HealthManager health;
    private LevelManager level;
    private ArrayList<Monster> monsters;
    private int time = 99;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 450,450);
        detection = new CollisionDetection();
        level = new LevelManager();
        health = new HealthManager((player.getHealth()));
        monsters = new ArrayList<>();
    }

    public void addMonster() {
        if (time % 100 == 0) {
            monsters.add(new Monster(game, (float)Math.random() * 611 + 150, 0));
        }

        if (time > 10000) {
            time = 0;
        }
    }

    public void tick() {
        time++;

        if (level.getProgressLevel() < 10) {
            addMonster();
        }

        for (int i = 0; i < monsters.size(); i++) {

            monsters.get(i).setSpeed(level.getLevel());

            monsters.get(i).tick();

            if (detection.hasCollided(monsters.get(i))) {
                monsters.get(i).explode(time);
            }
            else if (game.getKeyManager().getWordTyped().equals(monsters.get(i).getWord()) && !monsters.get(i).getExplosion() ) {
                monsters.get(i).explode(time);
                game.getKeyManager().resetWordTyped();
                level.setProgressLevel(level.getProgressLevel() + 1);
            }

            if (Math.abs(monsters.get(i).getTimeBlown() - time) > 30 && monsters.get(i).getExplosion()) {
                monsters.remove(i);
            }
        }
        if (level.getProgressLevel() == 10) {
            level.setLevel(level.getLevel() + 1);
            level.setProgressLevel(0);
        }

        player.tick();

        if (detection.getCollision() <= 0) {
            monsters.clear();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).getTexture() == -1) {
                monsters.get(i).setTexture((int) (4 * Math.random()));
            }


            if (monsters.get(i).getExplosion()) {
                monsters.get(i).renderExplosion(g);
            }

            else monsters.get(i).render(g);
        }

        health.render(g,detection);
        player.render(g);
        level.render(g);
    }

}
