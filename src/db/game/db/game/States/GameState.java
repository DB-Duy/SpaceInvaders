package db.game.db.game.States;

import db.game.db.game.Entities.Monster;
import db.game.db.game.Entities.Player;
import db.game.db.game.Entities.Shield;
import db.game.db.game.Game;
import db.game.db.game.LevelManagement.*;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;
    //private CollisionDetection detection;
    private HealthManager health;
    private LevelManager level;
    private ShieldManager shieldManager;
    private ArrayList<Shield> shields;
    private ArrayList<Monster> monsters;
    private int time = 99;
    private CollisionDetection monsterCollision, shieldCollision;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 450,450);
        //detection = new CollisionDetection();
        level = new LevelManager();
        shields = new ArrayList<>();
        shieldManager = new ShieldManager();
        health = new HealthManager(3);
        monsters = new ArrayList<>();
        monsterCollision = new CollisionDetection();
        shieldCollision = new CollisionDetection();
    }


    public void addMonster() {
        if (time % 100 == 0) {
            monsters.add(new Monster(game, (float)Math.random() * 611 + 150, 0));
        }

        if (time % 700 == 0 && level.getLevel() > 1) {
            shields.add(new Shield(game, (float)Math.random() * 611 + 150, 0));
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

        for (int i = 0; i < shields.size(); i++) {
            shields.get(i).setSpeed(level.getLevel());
            shields.get(i).tick();

            if (shieldCollision.hasCollided(shields.get(i))) {
                shields.get(i).explode(time);
            }
            else if (game.getKeyManager().getWordTyped().equals(shields.get(i).getWord()) && !shields.get(i).getExplosion() ) {
                shields.get(i).explode(time);
                game.getKeyManager().resetWordTyped();
                shieldManager.setShields(shieldManager.getShields() + 1);
                monsterCollision.setCollision(monsterCollision.getCollision() + 1);
            }
            if (Math.abs(shields.get(i).getBlownTime() - time) > 30 && shields.get(i).getExplosion()) {
                shields.remove(i);
            }
        }

        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setSpeed(level.getLevel());
            monsters.get(i).tick();

            if (monsterCollision.hasCollided(monsters.get(i))) {
                monsters.get(i).explode(time);
                if (shieldManager.getShields() > 0) {
                    shieldManager.setShields((shieldManager.getShields() - 1));
                }
                else health.setHealth(health.getHealth() - 1);
            }
            else if (game.getKeyManager().getWordTyped().equals(monsters.get(i).getWord()) && !monsters.get(i).getExplosion() ) {
                monsters.get(i).explode(time);
                game.getKeyManager().resetWordTyped();
                level.setProgressLevel(level.getProgressLevel() + 1);
            }
            if (Math.abs(monsters.get(i).getBlownTime() - time) > 30 && monsters.get(i).getExplosion()) {
                monsters.remove(i);
            }
        }

        if (level.getProgressLevel() == 10) {
            level.setLevel(level.getLevel() + 1);
            level.setProgressLevel(0);
        }

        player.tick();


        if (monsterCollision.getCollision() <= 0) {
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


        for (int i = 0; i < shields.size(); i++) {

            if (shields.get(i).getExplosion()) {
                shields.get(i).renderExplosion(g);
            }

            else shields.get(i).render(g);
        }

        shieldManager.render(g);
        health.render(g,monsterCollision);
        player.render(g);
        level.render(g);
    }

}
