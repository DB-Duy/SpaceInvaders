package db.game.Entities;

import db.game.FunctionManagement.*;
import db.game.Main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntityManager<T> {

    private ArrayList<ArrayList<Creature>> creatures;
    private Handler handler;
    private ShieldManager shield;
    private HealthManager health;
    private ScoreManager score;
    private Random random;
    private CollisionDetection collision;
    private ArrayList<Creature> list;

    public EntityManager(Handler handler    ) {
        creatures = new ArrayList<>();
        random = new Random();
        shield = new ShieldManager();
        health = new HealthManager(3);
        score = new ScoreManager(0);
        collision = new CollisionDetection();
        setHandler(handler);
    }

    public HealthManager getHealthManager() {
        return health;
    }


    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void add(ArrayList<Creature> something) {
        creatures.add(something);
    }

    public void addList(ArrayList<Creature> list){
        this.list = list;
    }

    public void setCollision(CollisionDetection collision) {
        this.collision = collision;
    }

    public void tick(LevelManager level, int time) {
        for(int i = 0; i < list.size();i++){
            list.get(i).setSpeed(level.getLevel());
            list.get(i).tick();
            if (collision.hasCollided(list.get(i))) {
                list.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                if (list.get(i).getClass().equals(Monster.class)) {
                    score.setNumKilled(0);
                    if (shield.getShields() > 0) {
                        shield.setShields((shield.getShields() - 1));
                    }
                    else health.setHealth(health.getHealth() - 1);
                }
            }
            else if (handler.getKeyManager().getWordTyped().equals(list.get(i).getWord()) && !list.get(i).getExplosion()) {
                list.get(i).explode(time);
                handler.getKeyManager().resetWordTyped();
                if(list.get(i).getClass().equals(Shield.class)){
                    score.shield();
                    shield.setShields(shield.getShields() + 1);
                }
                if(list.get(i).getClass().equals(Bomb.class)){
                    score.bomb();
                    if (shield.getShields() > 0) {
                        shield.setShields((shield.getShields() - 1));
                    }
                    else {
                        health.setHealth(health.getHealth() - 1);
                    }
                }
                if(list.get(i).getClass().equals(Monster.class)){
                    score.monster(); level.setProgressLevel(level.getProgressLevel() + 1);
                }
                if(list.get(i).getClass().equals(Asteroid.class)){
                    score.asteroid(); level.setMiniGame(true);
                }
                /*switch(i) {
                    case 0: score.shield(); shield.setShields(shield.getShields() + 1);
                        break;

                    case 1: score.bomb();
                        if (shield.getShields() > 0) {
                            shield.setShields((shield.getShields() - 1));
                        }
                        else {
                            health.setHealth(health.getHealth() - 1);
                        }
                        break;

                    case 2: score.monster(); level.setProgressLevel(level.getProgressLevel() + 1);
                        break;

                    case 3: score.asteroid(); level.setMiniGame(true);
                        break;
                }*/
            }
            if (Math.abs(list.get(i).getBlownTime() - time) > 30 && list.get(i).getExplosion()) {
                list.remove(i);
            }

        }
        }

        /*for (int i = 0; i < creatures.size(); i++) {
            for (int j = 0; j < creatures.get(i).size(); j++) {
                creatures.get(i).get(j).setSpeed(level.getLevel());
                creatures.get(i).get(j).tick();

                if (collision.hasCollided(creatures.get(i).get(j))) {
                    creatures.get(i).get(j).explode(time);
                    handler.getKeyManager().resetWordTyped();
                    if (i == 2) {
                        score.setNumKilled(0);
                        if (shield.getShields() > 0) {
                            shield.setShields((shield.getShields() - 1));
                        }
                        else health.setHealth(health.getHealth() - 1);
                    }
                }
                else if (handler.getKeyManager().getWordTyped().equals(creatures.get(i).get(j).getWord()) && !creatures.get(i).get(j).getExplosion()) {
                    creatures.get(i).get(j).explode(time);
                    handler.getKeyManager().resetWordTyped();
                    switch(i) {
                        case 0: score.shield(); shield.setShields(shield.getShields() + 1);
                        break;

                        case 1: score.bomb();
                        if (shield.getShields() > 0) {
                            shield.setShields((shield.getShields() - 1));
                        }
                        else {
                            health.setHealth(health.getHealth() - 1);
                        }
                        break;

                        case 2: score.monster(); level.setProgressLevel(level.getProgressLevel() + 1);
                        break;

                        case 3: score.asteroid(); level.setMiniGame(true);
                        break;
                    }
                }
                if (Math.abs(creatures.get(i).get(j).getBlownTime() - time) > 30 && creatures.get(i).get(j).getExplosion()) {
                    creatures.get(i).remove(j);
                }

            }
        }*/


    public void clear() {
        /*for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).clear();
        }*/
        list.clear();
    }

    public void render(Graphics g, int time) {
        /*for (int i = 0; i < creatures.size(); i++) {
            for (int j = 0; j < creatures.get(i).size(); j++) {
                if (creatures.get(i).get(j).getTexture() == -1) {
                    creatures.get(i).get(j).setTexture(random.nextInt(4));
                }
                if (creatures.get(i).get(j).getExplosion()) {
                    creatures.get(i).get(j).renderExplosion(g, time);
                }
                else {
                    creatures.get(i).get(j).render(g);
                }
            }
        }*/
        for(int i = 0; i < list.size(); i++){
            if( list.get(i).getTexture() == -1){
                list.get(i).setTexture(random.nextInt(4));
            }
            if(list.get(i).getExplosion()){
                list.get(i).renderExplosion(g,time);
            }
            else{
                list.get(i).render(g);
            }
        }

        shield.render(g);
        health.render(g);
        score.render(g);
    }




}
