package db.game.db.game.TextReader;

public class Read {
    ReadFile read=new ReadFile();
    public Read(){
        read.init();
    }
    public String randomWord(){
        return read.randomWord();
    }

}
