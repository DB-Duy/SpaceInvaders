package db.game.LevelManagement;
import db.game.Display.Assets;

import java.awt.*;

public class ShieldManager {

    private int shields = 0;

    public ShieldManager() {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setFont(new Font("Minecraft", Font.PLAIN, 22));
        g.drawString(this.shields + " x ", 230, 40);
        g.drawImage(Assets.shield, 260, 2, 60, 60, null);
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }
}
