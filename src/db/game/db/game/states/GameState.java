package db.game.db.game.states;

import db.game.db.game.Display.Assets;
import db.game.db.game.Game;
import db.game.db.game.Entities.*;
import db.game.db.game.LevelManagement.*;

import java.util.*;
import java.awt.*;

public class GameState extends State {

    private Player player;
    private CollisionDetection detection;
    private HealthManager health;
    private ArrayList<Monster> monsters;
    private int time = 99;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 200,650);
        detection = new CollisionDetection();
        health = new HealthManager((player.getHealth()));
        monsters = new ArrayList<>();
    }

    public void addMonster() {
        if (time % 100==0) {
            monsters.add(new Monster(game, (float)Math.random() * 301 + 50, 0));
        }
        if(time >10000){
            time=0;
        }
    }

    public void tick() {
        time++;
        if (monsters.size() < 10) {
            addMonster();
        }

        for (int i = 0; i < monsters.size(); i++) {

            monsters.get(i).tick();

            if (detection.hasCollided(monsters.get(i)) == true) {
                System.out.println(detection.getCollision());
                monsters.get(i).explode(time);
                monsters.get(i).setCurrentPosition();
            }

           /*if (monsters.get(i).getY() == 560 && monsters.get(i).getX() >= 110 && monsters.get(i).getX() <= 300) {
                collision++;
                System.out.println("Collided");
            }*/
            if (game.getKeyManager().getWordTyped().equals(monsters.get(i).getWord())) {
                monsters.get(i).setCurrentPosition();
                monsters.get(i).explode(time);
                game.getKeyManager().resetWordTyped();
            }
            if(Math.abs(monsters.get(i).getTimeBlown()-time)>30 && monsters.get(i).getExploded()){
                monsters.remove(i);
            }
        }
        player.tick();
        if(detection.getCollision()<=0){
            monsters.clear();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < monsters.size(); i++) {
            if(monsters.get(i).getTexture()==-1) {
                if (i % 3 == 0) {
                    monsters.get(i).setTexture(2);
                } else if (i % 2 == 0 && i % 3 != 0) {
                    monsters.get(i).setTexture(0);
                } else {
                    monsters.get(i).setTexture(1);
                }
            }
            if(monsters.get(i).getExploded()){
                monsters.get(i).renderExplode(g);
            }
            else monsters.get(i).render(g);
        }
        health.render(g, detection);
        player.render(g);
    }

}
