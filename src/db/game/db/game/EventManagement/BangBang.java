package db.game.db.game.EventManagement;

import java.awt.*;
import db.game.db.game.Display.Assets;
import db.game.db.game.Entities.Monster;

public class BangBang {

    private int x, y;
    private final int dimension = 40;
    public final int c = 480, d = 410;
    private int width, height;
    private int timeAppears;


    public BangBang() {
        this.x = 480;
        this.y = 410;
    }


    public void shoot(Monster monster) {

        // launch
        double angle1, angle2;
        double number1 = (double) (320 - monster.getB())/(monster.getA()-455), number2 = (double) (320 - monster.getB())/(455-monster.getA());

        angle1 = Math.atan(number1); angle2 = Math.atan(number2);
        System.out.println("a = " + monster.getA() + " b = " + monster.getB());
        System.out.println(angle1 + " " + angle2);


        if (y > monster.getB() + 90) {
            if (x > monster.getA() + 45) {
                x -= Math.cos(angle2) * 9;
            }
            if (x < monster.getA() + 45) {
                x += Math.cos(angle1) * 9;
            }
            y -= Math.abs(Math.sin(angle1)) * 9;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.rays.get(1), (int) x, (int) y, dimension, dimension, null);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reset() {
        this.x = c;
        this.y = d;
    }

    public void resetRender(Graphics g) {
        g.drawImage(Assets.rays.get(1), (int) x, (int) y, 0,0 , null);
    }

    public int getTimeAppears() {
        return timeAppears;
    }

    public void setTimeAppears(int timeAppears) {
        this.timeAppears = timeAppears;
    }
}