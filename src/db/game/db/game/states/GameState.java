package db.game.db.game.states;

import db.game.db.game.Game;
import db.game.db.game.display.Assets;
import db.game.db.game.entities.Monster;
import db.game.db.game.entities.Player;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private Monster monster1, monster2;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 200,650);
        monster1 = new Monster(game, (float) Math.random()*301 + 50, 0);
        monster2 = new Monster(game, (float) Math.random()*301 + 50, 0);
    }

    public void tick() {
        monster1.tick();
        monster2.tick();
        player.tick();
    }

    public void render(Graphics g) {
        monster1.render(g, 1);
        monster2.render(g, 1);
        player.render(g);
    }

}
