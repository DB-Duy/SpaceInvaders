package db.game.TextReader;

public class Text {
    ReadFile read = new ReadFile();
    public Text(){
        read.init();
    }
    public String randomWord(){
        return read.randomWord();
    }

}
