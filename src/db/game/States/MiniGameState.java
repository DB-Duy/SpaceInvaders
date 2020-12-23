package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Entities.Asteroid;
import db.game.Entities.Creature;
import db.game.Entities.EntityManager;
import db.game.Entities.Player;
import db.game.FunctionManagement.CollisionDetection;
import db.game.Input.KeyManager;
import db.game.Main.Handler;
import db.game.Sounds.Sound;
import db.game.UI.ImageButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MiniGameState extends State {

    private int health = 1;
    private Player player;
    private EntityManager entityManager;
    private CollisionDetection collision;
    private ArrayList<Asteroid> asteroids;
    private int incrementX;
    int time = 99;

    public MiniGameState(Handler handler) {
        super(handler);
        handler.getKeyManager().movable = true;
        handler.getKeyManager().typeable = false;
        entityManager = new EntityManager(handler);
        entityManager.setEntityManager(asteroids);
        collision = new CollisionDetection();
        Sound.stopLoop();
        Sound.playSoundLoop(".//res//sounds//minibg.wav");
        asteroids = new ArrayList<>();
        player = new Player(handler, 450,450);
    }

    public void addAsteroid() {

        if (time % 40 == 0) {
            asteroids.add(new Asteroid(handler, getEmptyX(), 0));
        }

        if (time > 10000) {
            time = 0;
        }
    }

    public float getEmptyX() {
        float x = (float) (Math.random()*611+150);
        if (asteroids.size() > 1) {
            if (x > asteroids.get(asteroids.size() - 1).getX() && x < asteroids.get(asteroids.size() - 1).getX() + asteroids.get(asteroids.size() - 1).getWidth()) {
                return getEmptyX();
            }
            else {
                return x;
            }
        }
        return x;
    }

    @Override
    public void tick() {
        time++;
        player.setX(player.getX() + handler.getKeyManager().increment);
        addAsteroid();
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).setWord("");
            asteroids.get(i).setSpeed(2);
            asteroids.get(i).tick();

            if (collision.hasCollided2(asteroids.get(i), player)) {
                asteroids.get(i).explode(time);
                health--;
            }

            if (Math.abs(asteroids.get(i).getBlownTime() - time) > 30 && asteroids.get(i).getExplosion()) {
                asteroids.remove(i);
                handler.getKeyManager().typeable = true;
                State.setState(handler.getGame().gameState);
            } 
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.loadImage("/resources/minibgnew.png"), 0, 0, 1000, 600, null);
        player.render(g);

        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).setWidth(Creature.DEFAULT_WIDTH + 30);
            asteroids.get(i).setHeight(Creature.DEFAULT_HEIGHT + 30);
            if (asteroids.get(i).getExplosion()) {
                asteroids.get(i).renderExplosion(g, time);
            }
            else {
                asteroids.get(i).render(g);
            }
        }
    }
}
