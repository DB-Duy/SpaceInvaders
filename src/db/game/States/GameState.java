package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Entities.*;
import db.game.LevelManagement.*;
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
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Shield> shields;
    private ArrayList<Monster> monsters;
    private ArrayList<Bomb> bombs;
    private int time = 99, y = 0;
    private CollisionDetection collision, shieldCollision, bombCollision, asteroidCollision;
    private ScoreManager score;
    private int numKilled;

    public GameState(Handler handler) {
        super(handler);
        player = new Player(handler, 450,450);
        level = new LevelManager();
        shields = new ArrayList<>();
        bombs = new ArrayList<>();
        shieldManager = new ShieldManager();
        health = new HealthManager(3);
        monsters = new ArrayList<>();
        asteroids = new ArrayList<>();
        collision = new CollisionDetection();
        shieldCollision = new CollisionDetection();
        asteroidCollision = new CollisionDetection();
        bombCollision = new CollisionDetection();
        score = new ScoreManager(0);
        numKilled = 0;
    }


    public void addCreature() {
        if (time % 50 == 0) {
            monsters.add(new Monster(handler, (float) Math.random() * 611 + 150, 0));
        }

        if (level.getLevel() > 1) {
            if (time % 400 == 0) {
                bombs.add(new Bomb(handler, (float) Math.random() * 611 + 150, 0));
            }
            else if (time % 500 == 0) {
                shields.add(new Shield(handler, (float) Math.random() * 611 + 150, 0));
            }
        }

        //if (level.getLevel() > 1) {
            if (time % 300 == 0) {
                asteroids.add(new Asteroid(handler, (float) Math.random() * 611 + 150, 0));
            }
        //}


        if (time > 10000) {
            time = 0;
        }
    }

    public void tick() {
        time++; y++;

        if (y > handler.getGame().getHeight()) {
            y = 0;
        }

        if (level.getProgressLevel() < 10) {
            addCreature();
        }

        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).setSpeed(level.getLevel());
            asteroids.get(i).tick();

            if (asteroidCollision.hasCollided(asteroids.get(i))) {
                asteroids.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                collision.setCollision(collision.getCollision() + 1);
            }
            else if (handler.getKeyManager().intValue() == (asteroids.get(i).getA() + asteroids.get(i).getB()) && !asteroids.get(i).getExplosion()) {
                asteroids.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                score.asteroid();
                collision.setCollision(collision.getCollision() + 1);
            }
            if (Math.abs(asteroids.get(i).getBlownTime() - time) > 30 && asteroids.get(i).getExplosion()) {
                asteroids.remove(i);
            }
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
                score.shield();
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
                numKilled = 0;
                score.bomb();
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
                numKilled = 0;
                if (shieldManager.getShields() > 0) {
                    shieldManager.setShields((shieldManager.getShields() - 1));
                }
                else health.setHealth(health.getHealth() - 1);
            }
            else if (handler.getKeyManager().getWordTyped().equals(monsters.get(i).getWord()) && !monsters.get(i).getExplosion()) {
                monsters.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                numKilled++;
                monsters.get(i).scoring(numKilled, score);
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

        if (health.getHealth() == 0) {
            asteroids.clear();
            monsters.clear();
            shields.clear();
            bombs.clear();
            State.setState(handler.getGame().deathState);
        }
    }



    public void render(Graphics g) {
        g.drawImage(ImageLoader.loadImage("/resources/background.png"),0, 0, null);
        g.drawImage(Assets.stars, 0, y, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.stars,0,y - handler.getHeight(), handler.getGame().getWidth(), handler.getGame().getHeight(),null);
        g.drawImage(Assets.mountains,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).getTexture() == -1) {
                monsters.get(i).setTexture((int) (4 * Math.random()));
            }

            if (monsters.get(i).getExplosion()) {
                monsters.get(i).renderExplosion(g,time);
            }

            else monsters.get(i).render(g);
        }

        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getExplosion()) {
                bombs.get(i).renderExplosion(g, time);
            }

            else bombs.get(i).render(g);
        }

        for (int i = 0; i < shields.size(); i++) {

            if (shields.get(i).getExplosion()) {
                shields.get(i).renderExplosion(g, time);
            }

            else shields.get(i).render(g);
        }

        for (int i = 0; i < asteroids.size(); i++) {

            if (asteroids.get(i).getExplosion()) {
                asteroids.get(i).renderExplosion(g, time);
            }

            else asteroids.get(i).render(g);
        }


        g.drawImage(Assets.blackBar,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        score.render(g);
        health.render(g);
        player.render(g);
        level.render(g);
        shieldManager.render(g);
    }

}
