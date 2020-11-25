package db.game.db.game.States;

import db.game.db.game.Game;
import db.game.db.game.Entities.*;
import db.game.db.game.EventManagement.CollisionDetection;
import db.game.db.game.EventManagement.HealthManager;
import db.game.db.game.EventManagement.LevelManager;
import db.game.db.game.EventManagement.BangBang;

import java.util.*;
import java.awt.*;

public class GameState extends State {

    private BangBang ray;
    private Player player;
    private CollisionDetection detection;
    private HealthManager health;
    private LevelManager level;
    private ArrayList<Monster> monsters;
    private boolean typed = false;
    private int time = 99;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 450,450);
        detection = new CollisionDetection();
        level = new LevelManager();
        health = new HealthManager((player.getHealth()));
        monsters = new ArrayList<>();
        ray = new BangBang();

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

        int r = 0;

        for (int i = 0; i < monsters.size(); i++) {

            monsters.get(i).setSpeed(level.getLevel());

            monsters.get(i).tick();

            if (detection.hasCollided(monsters.get(i))) {
                monsters.get(i).explode(time);
            }

            else if (game.getKeyManager().getWordTyped().equals(monsters.get(i).getWord()) && !monsters.get(i).getExplosion()) {
                typed = true;
                r = i;
                monsters.get(i).explode(time);
                game.getKeyManager().resetWordTyped();
                level.setProgressLevel(level.getProgressLevel() + 1);
            }

            if (typed == true) {
                ray.shoot(monsters.get(r));
            }

            if (Math.abs(monsters.get(i).getTimeBlown() - time) > 30 && monsters.get(i).getExplosion()) {
                ray.reset();
                typed = false;
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
        if (typed == true) {
            ray.render(g);
        }

        for (int i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).getTexture() == -1) {
                monsters.get(i).setTexture((int) (4 * Math.random()));
            }

            if (ray.getY() == monsters.get(i).getY() + 90) {
                ray.resetRender(g);
                monsters.get(i).renderExplosion(g);
            }

            else if (monsters.get(i).getExplosion() && typed == false) {
                monsters.get(i).renderExplosion(g);
            }


            else monsters.get(i).render(g);
        }


        health.render(g, detection);
        player.render(g);
        level.render(g);
    }

}
