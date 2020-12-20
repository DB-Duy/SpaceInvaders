package db.game.UI;

import db.game.Input.MouseManager;
import db.game.Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageButton {

    private ArrayList<BufferedImage> buttons;
    private Handler handler;
    private int x, y, width, height;
    private boolean hovering;

    public ImageButton(Handler handler, ArrayList<BufferedImage> buttons, int x, int y, int width, int height) {
        this.handler = handler;
        this.buttons = buttons;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isHovering() {
        Rectangle bounds = new Rectangle(x, y, width, height);
        if (bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
            return true;
        }
        else {
            return false;
        }
    }

    public void render(Graphics g) {
        if (isHovering()) {
            g.drawImage(buttons.get(1), x, y, width, height, null);
        }
        else {
            g.drawImage(buttons.get(0), x, y, width, height, null);
        }
    }
}
