package db.game.States;

import db.game.Display.ImageLoader;
import db.game.Entities.*;
import db.game.LevelManagement.CollisionDetection;
import db.game.LevelManagement.HealthManager;
import db.game.LevelManagement.LevelManager;
import db.game.LevelManagement.ShieldManager;
import db.game.Main.Game;
import db.game.Main.Handler;
import db.game.Sounds.Sound;

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
    private ArrayList<Bomb> bombs;
    private int time = 99;
    private CollisionDetection collision, shieldCollision, bombCollision;

    public GameState(Handler handler) {
        super(handler);
        player = new Player(handler, 450,450);
        level = new LevelManager();
        shields = new ArrayList<>();
        bombs = new ArrayList<>();
        shieldManager = new ShieldManager();
        health = new HealthManager(3);
        monsters = new ArrayList<>();
        collision = new CollisionDetection();
        shieldCollision = new CollisionDetection();
        bombCollision = new CollisionDetection();
    }


    public void addCreature() {
        if (time % 100 == 0) {
            monsters.add(new Monster(handler, (float) Math.random() * 611 + 150, 0));
        }

        if (time % 700 == 0 && level.getLevel() > 1) {
            shields.add(new Shield(handler, (float) Math.random() * 611 + 150, 0));
        }

        if (time % 900 == 0 && level.getLevel() > 1) {
            bombs.add(new Bomb(handler, (float) Math.random() * 611 + 150, 0));
        }

        if (time > 10000) {
            time = 0;
        }
    }

    public void tick() {
        time++;

        if (level.getProgressLevel() < 10) {
            addCreature();
        }

        for (int i = 0; i < shields.size(); i++) {
            shields.get(i).setSpeed(level.getLevel());
            shields.get(i).tick();

            if (shieldCollision.hasCollided(shields.get(i))) {
                shields.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                collision.setCollision(collision.getCollision() + 1);
            }
            else if (handler.getKeyManager().getWordTyped().equals(shields.get(i).getWord()) && !shields.get(i).getExplosion()) {
                shields.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                shieldManager.setShields(shieldManager.getShields() + 1);
                collision.setCollision(collision.getCollision() + 1);
            }
            if (Math.abs(shields.get(i).getBlownTime() - time) > 30 && shields.get(i).getExplosion()) {
                shields.remove(i);
            }
        }


        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).setSpeed(level.getLevel());
            bombs.get(i).tick();

            if (bombCollision.hasCollided(bombs.get(i))) {
                bombs.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                collision.setCollision(collision.getCollision() + 1);
            }
            else if (handler.getKeyManager().getWordTyped().equals(bombs.get(i).getWord()) && !bombs.get(i).getExplosion()) {
                bombs.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                if (shieldManager.getShields() > 0) {
                    shieldManager.setShields((shieldManager.getShields() - 1));
                }
                else health.setHealth(health.getHealth() - 1);
                collision.setCollision(collision.getCollision() - 1);
            }
            if (Math.abs(bombs.get(i).getBlownTime() - time) > 30 && bombs.get(i).getExplosion()) {
                bombs.remove(i);
            }
        }


        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setSpeed(level.getLevel());
            monsters.get(i).tick();

            if (collision.hasCollided(monsters.get(i))) {
                monsters.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                if (shieldManager.getShields() > 0) {
                    shieldManager.setShields((shieldManager.getShields() - 1));
                }
                else health.setHealth(health.getHealth() - 1);
            }
            else if (handler.getKeyManager().getWordTyped().equals(monsters.get(i).getWord()) && !monsters.get(i).getExplosion()) {
                monsters.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
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


        if (collision.getCollision() == 0) {
            monsters.clear();
            shields.clear();
            bombs.clear();
            State.setState(handler.getGame().deathState);
        }
    }

    public void render(Graphics g) {
        g.drawImage(ImageLoader.loadImage("/resources/background.png"),0, 0, null);

        for (int i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).getTexture() == -1) {
                monsters.get(i).setTexture((int) (4 * Math.random()));
            }

            if (monsters.get(i).getExplosion()) {
                monsters.get(i).renderExplosion(g);
            }

            else monsters.get(i).render(g);
        }

        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getExplosion()) {
                bombs.get(i).renderExplosion(g);
            }

            else bombs.get(i).render(g);
        }

        for (int i = 0; i < shields.size(); i++) {

            if (shields.get(i).getExplosion()) {
                shields.get(i).renderExplosion(g);
            }

            else shields.get(i).render(g);
        }

        health.render(g);
        player.render(g);
        level.render(g);
        shieldManager.render(g);
    }

}
