package db.game.States;

import db.game.Main.Handler;
import java.io.*;
import java.awt.*;
import java.util.HashMap;

public class LeaderboardState extends State {
    private HashMap<String,Integer> leaderboard;
    private File leaderboardFile = new File(".//res//leaderboard//leaderboard.txt");
    public LeaderboardState(Handler handler) {
        super(handler);
        init();
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }
    public void init(){
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
    public void addToLeaderboard(String name, int score){
        this.leaderboard.put(name,score);
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
    public void clearLeaderboard(){
        this.leaderboard.clear();
    }
}
