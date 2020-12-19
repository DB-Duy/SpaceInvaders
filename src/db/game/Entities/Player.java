package db.game.Entities;

import db.game.Input.MouseManager;
import db.game.Main.Game;
import db.game.Display.Assets;
import db.game.Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;


public class Player extends Creature implements MouseMotionListener {

    private double angle = 0, dx = 0, dy = 0;
    private BufferedImage image;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        image = Assets.rocket;
        handler.getGame().getDisplay().getFrame().addMouseMotionListener(this);
        handler.getGame().getDisplay().getCanvas().addMouseMotionListener(this);
    }


    public void tick() {
        //dx -= 450;
        //dy -= 400;

        angle = Math.toDegrees(Math.atan2(dy,dx)); System.out.println(angle);
        //dx = 0; dy = 0;
    }

    public BufferedImage rotateImage(BufferedImage image, double angle) {
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, image.getType());
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(angle), w/2, h/2);
        g2d.drawImage(image, null, 0, 0);
        g2d.dispose();

        return rotated;
    }

    public void render(Graphics g) {

        g.drawImage(rotateImage(image, angle), (int) x, (int) y, width, height, null);

        g.setColor(yellow);

        FontMetrics metrics = g.getFontMetrics(font.deriveFont(22f));
        int c = (int) this.x + (this.width - metrics.stringWidth(handler.getKeyManager().getWordTyped())) / 2;

        g.setFont(font.deriveFont(22f));
        g.drawString(handler.getKeyManager().getWordTyped(),c ,(int) y + 110);
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        dx = e.getX() - 450;
        dy = e.getY() - 430;
    }
}