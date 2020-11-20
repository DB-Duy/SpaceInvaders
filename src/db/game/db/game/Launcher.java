package db.game.db.game;

import db.game.db.game.textreader.Read;

public class Launcher {
    public static void main(String[] args){
        Game game=new Game("Cancer",1280,720);
        game.start();
    }
}
