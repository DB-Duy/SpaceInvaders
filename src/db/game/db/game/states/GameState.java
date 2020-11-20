package db.game.db.game.states;

import db.game.db.game.Game;
import db.game.db.game.Entities.Monster;
import db.game.db.game.Entities.Player;
import java.util.*;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private ArrayList<Monster> monsters;
    private double time = 99;


    public GameState(Game game) {
        super(game);
        player = new Player(game, 200,650);
        monsters = new ArrayList<>();
    }
    public void addMonster(){
        if (time > 100) {
            time = 0;
            monsters.add(new Monster(game, (float)Math.random() * 301 + 50, 0));
        }
    }
    public void tick() {
        time++;
        if (monsters.size() < 10) {
            addMonster();
        }
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).tick();
            if(game.getKeyManager().getWordTyped().equals(monsters.get(i).getWord())){
                monsters.remove(i);
                game.getKeyManager().resetWordTyped();
            }
        }
        player.tick();
    }

    public void render(Graphics g) {
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).render(g,1);
        }
        player.render(g);
    }

}
