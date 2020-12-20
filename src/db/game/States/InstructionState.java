package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InstructionState extends State {

    private ImageButton back, next;
    private ArrayList<ImageButton> buttons;
    private boolean backHovered, nextHovered;
    private BufferedImage instructionImage;
    private int i = 0;


    public InstructionState(Handler handler) {
        super(handler);
        instructionImage = Assets.instructionScreens.get(i);

        back = new ImageButton(handler, Assets.back, 10, 30, 250, 45);
        next = new ImageButton(handler, Assets.next, 790, 30, 250, 45);

        buttons = new ArrayList<>();
        buttons.add(back);
        buttons.add(next);
    }

    @Override
    public void tick() {

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                switch(i) {
                    case 0:
                        if (i == 0) {
                            State.setState(handler.getGame().menuState);
                        }
                        else {
                            i--;
                            setInstructionImage(i);
                        }
                        break;
                    case 1:
                        i++;
                        setInstructionImage(i);
                        break;
                }
            }
        }

    }

    public void setInstructionImage(int i) {
        instructionImage = Assets.instructionScreens.get(i);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(instructionImage, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }
}
