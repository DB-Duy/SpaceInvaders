package db.game.LevelManagement;

import java.awt.*;

public class ScoreManager {

    private int score;

    public ScoreManager(int score) {
        this.score = score;
    }

    public int individual() {
        return 10;
    }

    public int combo_1() {
        return 20;
    }

    public int combo_2() {
        return 30;
    }

    public int combo_3() {
        return 40;
    }

    public int shield() {
        return 5;
    }

    public int bomb() {
        return (-1)*5;
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
