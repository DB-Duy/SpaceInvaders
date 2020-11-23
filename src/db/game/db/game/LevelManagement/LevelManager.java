package db.game.db.game.LevelManagement;
import db.game.db.game.Display.Assets;

import java.awt.*;


public class LevelManager {

    private int progressLevel = 0;
    private int level = 1;

    public LevelManager() {


    }

    public void tick() {

    }

    public int getProgressLevel() {
        return progressLevel;
    }

    public void setProgressLevel(int progressLevel) {
        this.progressLevel = progressLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void render(Graphics g) {
        g.setFont(new Font("Minecraft", Font.PLAIN, 22));
        g.drawString("LVL " + this.level, 730, 45);

        if (progressLevel == 0) {
            g.drawImage(Assets.levelBars.get(9), 790, 0, 180, 70, null);
        }
        else if (progressLevel == 10) {
            g.drawImage(Assets.levelBars.get(10), 790, 0, 180, 70, null);
        }
        else {
            g.drawImage(Assets.levelBars.get(progressLevel-1), 790, 0, 180, 70, null);
        }

    }


}
