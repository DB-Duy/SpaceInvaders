package db.game.TextReader;

public class Text {
    private String path;
    private ReadFile read;
    public Text(String path){
        this.read = new ReadFile(path);
        read.init();
    }

    public String randomWord(){
        return read.randomWord();
    }

}
