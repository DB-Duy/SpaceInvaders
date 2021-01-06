package db.game.Entities;

import db.game.FunctionManagement.*;
import db.game.Main.Handler;
import db.game.Sounds.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntityManager<T> {

    private Handler handler;
    private ShieldManager shield;
    private HealthManager health;
    private ScoreManager score;
    private Random random;
    private CollisionDetection collision;
    private ArrayList<Creature> creatures;
    private boolean isDead = false;

    public EntityManager(Handler handler) {
        creatures = new ArrayList<>();
        random = new Random();
        shield = new ShieldManager();
        health = new HealthManager(3);
        score = new ScoreManager(0);
        collision = new CollisionDetection();

        setHandler(handler);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public HealthManager getHealthManager() {
        return health;
    }

    public ScoreManager getScoreManager() {
        return score;
    }


    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    public void setEntityManager(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public void setCollision(CollisionDetection collision) {
        this.collision = collision;
    }

    public void tick(LevelManager level, int time) {
        for (int i = 0; i < creatures.size(); i++) {
            if (isDead) {
                creatures.get(i).setSpeed(0);
            }
            else {
                creatures.get(i).setSpeed(level.getLevel() + 1);
                creatures.get(i).tick();

                if (collision.hasCollided(creatures.get(i))) {
                    creatures.get(i).explode(time);
                    handler.getKeyManager().resetWordTyped();

                    if (creatures.get(i).getClass().equals(Monster.class)) {
                        score.setNumKilled(0);

                        if (shield.getShields() > 0) {
                            shield.setShields((shield.getShields() - 1));
                        }

                        else {
                            health.setHealth(health.getHealth() - 1);
                        }
                    }
                }

                else if (handler.getKeyManager().getWordTyped().equals(creatures.get(i).getWord()) && !creatures.get(i).getExplosion()) {
                    creatures.get(i).explode(time);
                    handler.getKeyManager().resetWordTyped();

                    if (creatures.get(i).getClass().equals(Shield.class)) {
                        score.shield();
                        shield.setShields(shield.getShields() + 1);
                    }

                    if (creatures.get(i).getClass().equals(Bomb.class)) {
                        score.bomb();
                        if (shield.getShields() > 0) {
                            shield.setShields((shield.getShields() - 1));
                        }
                        else {
                            health.setHealth(health.getHealth() - 1);
                        }
                    }

                    if (creatures.get(i).getClass().equals(Monster.class)) {
                        score.monster(); level.setProgressLevel(level.getProgressLevel() + 1);
                    }

                    if (creatures.get(i).getClass().equals(Asteroid.class)) {
                        score.asteroid(); level.setMiniGame(true);
                    }
                }


                if (Math.abs(creatures.get(i).getBlownTime() - time) > 30 && creatures.get(i).getExplosion()) {
                    creatures.remove(i);
                }
            }
        }
    }

    public float getEmptyX() {
        float x = (float) (Math.random()*611+150);
        if (creatures.size() > 1) {
            if (x > creatures.get(creatures.size() - 1).getX() - creatures.get(creatures.size() - 1).getWidth() && x < creatures.get(creatures.size() - 1).getX() + creatures.get(creatures.size() - 1).getWidth()) {
                return getEmptyX();
            }
            else {
                return x;
            }
        }
        return x;
    }

    public void clear() {
        creatures.clear();
    }

    public void render(Graphics g, int time) {

        for(int i = 0; i < creatures.size(); i++) {

            if (creatures.get(i).getTexture() == -1) {
                creatures.get(i).setTexture(random.nextInt(4));
            }

            if (creatures.get(i).getExplosion()) {
                creatures.get(i).renderExplosion(g,time);
            }

            else {
                creatures.get(i).render(g);
            }
        }

        shield.render(g);
        health.render(g);
        score.render(g);
    }




}
