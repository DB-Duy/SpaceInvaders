package db.game.FunctionManagement;

import db.game.Main.Handler;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard {
    private HashMap<String,Integer> leaderboard;
    public ArrayList<String> userNames = new ArrayList<>();
    public ArrayList<Integer> scores = new ArrayList<>();
    private File leaderboardFile = new File(".//res//leaderboard//leaderboard.txt");
    private static Leaderboard boardInstance = null;

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    private Leaderboard() {
        init();
        //clearLeaderboard();
        updateArrays();
    }

    public void updateArrays(){
        userNames.clear();
        scores.clear();
        for(Object name : this.getLeaderboard().keySet()){
            userNames.add((String) name);
        }
        for(Object score: this.getLeaderboard().values()){
            scores.add((Integer) score);
        }
    }

    public static Leaderboard getLeaderboardInstance() {
        if (boardInstance == null) {
            boardInstance = new Leaderboard();
        }
        return boardInstance;
    }
    public void changeUserName(String oldName, String newName){
        int score;
        if(leaderboard.containsKey(oldName)){
            score = leaderboard.get(oldName);
            leaderboard.remove(oldName);
            leaderboard.put(newName,score);
        }
        else{
            System.out.println("Old name doesn't exist");
        }

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
        updateLeaderboard();
    }

    public void addToLeaderboard(String name) {
        this.leaderboard.put(name,0);
        updateLeaderboard();
    }
    public int getPlayerScore(String name){
        return leaderboard.get(name);
    }

    public void updateScore(String name, int score) {
        if (leaderboard.containsKey(name)) {
            leaderboard.put(name,score);
            updateLeaderboard();
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
        updateArrays();
    }

    public void clearLeaderboard() {
        this.leaderboard.clear();
    }

    public HashMap getLeaderboard() {
        return leaderboard;
    }
}
