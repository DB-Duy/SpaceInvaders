package db.game.TextReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class ReadFile{
    static File file;
    static Scanner readFile;
    Random rand;
    private static ArrayList<String> words;

    public ReadFile(String path) {
        file = new File(path);
        rand = new Random();
        words = new ArrayList<>();

        try {
            readFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        while (readFile.hasNextLine()) {
            words.add(readFile.nextLine());
        }
    }

    public String randomWord(){
        return words.get(rand.nextInt(words.size()));
    }
}
