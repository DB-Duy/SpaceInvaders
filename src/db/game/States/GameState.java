package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Entities.*;
import db.game.FunctionManagement.*;
import db.game.Main.Handler;
import db.game.Sounds.Sound;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;

    private EntityManager entityManager;

    private LevelManager level;

    private ArrayList<Asteroid> asteroids;
    private ArrayList<Shield> shields;
    private ArrayList<Monster> monsters;
    private ArrayList<Bomb> bombs;

    private int time = 99, y = 0;
    private CollisionDetection collision;


    public GameState(Handler handler) {
        super(handler);
        player = new Player(handler, 450,450);

        level = new LevelManager();

        shields = new ArrayList<>();
        bombs = new ArrayList<>();
        monsters = new ArrayList<>();
        asteroids = new ArrayList<>();

        entityManager = new EntityManager(handler);
        entityManager.add(shields);
        entityManager.add(bombs);
        entityManager.add(monsters);
        entityManager.add(asteroids);

        Sound.playSoundLoop(".//res//sounds//background.wav");

        collision = new CollisionDetection();
    }


    public void addCreature() {
        if (time % 50 == 0) {
            //monsters.add(new Monster(handler, (float) Math.random() * 611 + 150, 30));
            monsters.add(new Monster(handler, emptyX(), 30));
        }

        if (level.getLevel() > 1) {
            if (time % 400 == 0) {
                bombs.add(new Bomb(handler, (float) Math.random() * 611 + 150, 30));
            }
            else if (time % 500 == 0) {
                shields.add(new Shield(handler, (float) Math.random() * 611 + 150, 30));
            }
        }

            if (time % 300 == 0) {
                asteroids.add(new Asteroid(handler, (float) Math.random() * 611 + 150, 30));
            }


        if (time > 100000) {
            time = 0;
        }
    }

    public void tick() {
        time++; y++;

        if (level.isMiniGame()) {
            State.setState(null);
            State.setState(new MiniGameState(handler));
            level.setMiniGame(false);
        }

        if (y > handler.getGame().getHeight()) {
            y = 0;
        }

        if (level.getProgressLevel() < 10) {
            addCreature();
        }

        entityManager.tick(level, time);


        if (level.getProgressLevel() == 10) {
            level.setLevel(level.getLevel() + 1);
            level.setProgressLevel(0);
        }

        player.tick();

        if (entityManager.getHealthManager().getHealth() == 0) {
            entityManager.clear();
            State.setState(new DeathState(handler));
        }
    }



    public void render(Graphics g) {
        g.drawImage(ImageLoader.loadImage("/resources/bg.png"),0, 0, null);
        g.drawImage(Assets.stars, 0, y, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.stars,0,y - handler.getHeight(), handler.getGame().getWidth(), handler.getGame().getHeight(),null);
        g.drawImage(Assets.mountains,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.blackBar,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        entityManager.render(g, time);
        level.render(g);
        player.render(g);

    }
    public float emptyX(){
        float x=(float)(Math.random()*611+150);
        boolean isEmpty=true;
        if(monsters.size()>1) {
            if (x > monsters.get(monsters.size() - 1).getX() - monsters.get(monsters.size() - 1).getWidth() && x < monsters.get(monsters.size() - 1).getX() + monsters.get(monsters.size() - 1).getWidth()) {
                return emptyX();
            } else
                return x;
        }
        else return x;
    }
}
