package db.game.LevelManagement;

import java.awt.*;

public class ScoreManager {

    private int score;

    public ScoreManager(int score) {
        this.score = score;
    }

    public void individual() {
        setScore(getScore() + 10);
    }

    public void combo_1() {
        setScore(getScore() + 20);
    }

    public void combo_2() {
        setScore(getScore() + 30);
    }

    public void combo_3() {
        setScore(getScore() + 40);
    }

    public void shield() {
        setScore(getScore() + 5);
    }

    public void bomb() {
        setScore(getScore() - 5);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setFont(new Font("Minecraft", Font.PLAIN, 22));
        g.drawString("SCORE  " + this.score, 500, 42);
    }
}
