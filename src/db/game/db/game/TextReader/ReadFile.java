package db.game.db.game.TextReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class ReadFile{
    static File file=new File("level1.txt");
    static Scanner readFile;
    Random rand=new Random();
    private static ArrayList<String> words= new ArrayList();


    static {
        try {
            readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void init(){
        Random rand=new Random();
        while(readFile.hasNextLine()){
            words.add(readFile.nextLine());
        }
    }
    public String randomWord(){
        return words.get(rand.nextInt(words.size() ));
    }
}
