package db.game.db.game.textreader;

public class Read {
    ReadFile read=new ReadFile();
    public Read(){
        read.init();
    }
    public String randomWord(){
        return read.randomWord();
    }

}
