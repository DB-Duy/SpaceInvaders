package db.game.FunctionManagement;

import db.game.Entities.Shield;

import java.awt.*;

public class ScoreManager extends Functionality {

    private int score, numKilled;

    public ScoreManager(int score) {
        this.score = score;
        numKilled = 0;
    }

    public int getNumKilled() {
        return numKilled;
    }

    public void setNumKilled(int numKilled) {
        this.numKilled = numKilled;
    }

    public void monster() {
        numKilled++;
        if (numKilled > 0) {
            if (numKilled < 5) {
                individual();
            }
            else if (numKilled < 10) {
                combo_1();
            }
            else if (numKilled < 20) {
                combo_2();
            }
            else if (numKilled >= 20) {
                combo_3();
            }
        }
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

    public void asteroid() {
        setScore(getScore() + 5);
    }

    public void bomb() {
        numKilled = 0;
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
