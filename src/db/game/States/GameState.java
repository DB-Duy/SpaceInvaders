package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Entities.*;
import db.game.FunctionManagement.*;
import db.game.Main.Handler;
import db.game.Sounds.Sound;
import db.game.UI.ImageButton;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;

    private ImageButton gameOverFlash;

    private EntityManager entityManager;

    private LevelManager level;

    private ArrayList<Creature> creatures;

    private int time = 99, y = 0;
    private CollisionDetection collision;


    public GameState(Handler handler) {
        super(handler);
        player = new Player(handler, 450,450);

        level = new LevelManager();

        creatures = new ArrayList<>();

        entityManager = new EntityManager(handler);
        entityManager.setEntityManager(creatures);
        Sound.stopLoop();
        Sound.playSoundLoop(".//res//sounds//background.wav");


        collision = new CollisionDetection();
        gameOverFlash = new ImageButton(handler, Assets.gameOverFlash, 100, 220, 802, 150);
    }

    public Player getPlayer() {
        return player;
    }

    public void addCreature() {
        if (time % 50 == 0) {
            creatures.add(new Monster(handler, entityManager.getEmptyX(), 30));
        }

        if (level.getLevel() > 1) {
            if (time % 100 == 0) {
                creatures.add(new Bomb(handler, entityManager.getEmptyX(), 30));
            }
            else if (time % 200 == 0) {
                creatures.add(new Shield(handler, entityManager.getEmptyX(), 30));
            }
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
        player.tick();

        if (level.getProgressLevel() == 10) {
            level.setLevel(level.getLevel() + 1);
            level.setProgressLevel(0);
        }

        if (entityManager.getHealthManager().getHealth() <= 0) {
            Sound.stopLoop();
            entityManager.setDead(true);
            entityManager.clear();
            handler.getGame().playerScore = entityManager.getScoreManager().getScore();
            if (gameOverFlash.isHovering() && handler.getMouseManager().isLeftPressed()) {
                State.setState(new DeathState(handler));
            }
        }
    }


    public void render(Graphics g) {
        g.drawImage(ImageLoader.loadImage("/resources/bg.png"),0, 0, null);
        g.drawImage(Assets.stars, 0, y, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.stars,0,y - handler.getHeight(), handler.getGame().getWidth(), handler.getGame().getHeight(),null);
        g.drawImage(Assets.mountains,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.blackBar,0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        entityManager.render(g, time);
        player.render(g);
        level.render(g);

        if (entityManager.isDead()) {
            gameOverFlash.render(g);
        }

    }
}
