package db.game.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UIImageButton extends UIObject {

    private ArrayList<BufferedImage> images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, ArrayList<BufferedImage> images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if (hovering) {
            g.drawImage(images.get(1), (int) x, (int) y, width, height, null);
        }
        else {
            g.drawImage(images.get(0), (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}

