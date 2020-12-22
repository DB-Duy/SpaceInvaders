package db.game.Main;

import db.game.Input.KeyManager;
import db.game.Input.MouseManager;

public class Handler {

    private Game game;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public void setKeyManager(KeyManager keyManager) {
        game.setKeyManager(keyManager);
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
