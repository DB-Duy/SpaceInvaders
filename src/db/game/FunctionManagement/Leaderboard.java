package db.game.FunctionManagement;

import db.game.Main.Handler;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class Leaderboard {
    private HashMap<String,Integer> leaderboard;
    private File leaderboardFile = new File(".//res//leaderboard//leaderboard.txt");
    private static Leaderboard boardInstance = null;

    private Leaderboard() {
        init();
    }

    public static Leaderboard getLeaderboardInstance() {
        if (boardInstance == null) {
            boardInstance = new Leaderboard();
        }
        return boardInstance;
    }

    public void init() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(leaderboardFile));
            this.leaderboard = (HashMap) input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addToLeaderboard(String name, int score) {
        this.leaderboard.put(name,score);
    }


    public void addToLeaderboard(String name) {
        this.leaderboard.put(name,0);
    }


    public void updateScore(String name, int score) {
        if (leaderboard.containsKey(name)) {
            leaderboard.put(name,score);
        }
        else {
            System.out.println("User doesn't exist, please call the addToLeaderboard method instead");
        }
    }

    public void updateLeaderboard() {

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream((leaderboardFile)));
            output.writeObject(leaderboard);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(leaderboard.toString());
    }

    public void clearLeaderboard() {
        this.leaderboard.clear();
    }

    public HashMap getLeaderboard() {
        return leaderboard;
    }
}
