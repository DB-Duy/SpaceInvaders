package db.game.db.game.states;

import db.game.db.game.Game;
import db.game.db.game.display.Assets;
import db.game.db.game.entities.Player;

import java.awt.*;

public class GameState extends State {
    private Player player;

    public GameState(Game game){
        super(game);
        player=new Player(100,100);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
}
