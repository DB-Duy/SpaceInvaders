package db.game.db.game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Space Invaders",1000,600);
        game.start();
    }
}