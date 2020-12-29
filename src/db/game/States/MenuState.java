package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends State {

    private ImageButton start, settings, instructions, quit, select;
    private ArrayList<ImageButton> buttons;
    private int y = 0;


    public MenuState(Handler handler) {
        super(handler);

        /*start = new ImageButton(handler, Assets.startButtons, 375, 300, 240,40);
        settings = new ImageButton(handler, Assets.settings, 375, 360, 240,40);
        select = new ImageButton(handler, Assets.selectButtons, 375, 420, 240, 40);
        instructions = new ImageButton(handler, Assets.instructions, 375, 480, 240,40);
        quit = new ImageButton(handler, Assets.quitButtons, 375, 540, 240,40);*/

        start = new ImageButton(handler, Assets.startButtons, 220, 340, 280,45);
        //settings = new ImageButton(handler, Assets.settings, 520, 320, 240,40);
        select = new ImageButton(handler, Assets.selectButtons, 490, 340, 280, 45);
        instructions = new ImageButton(handler, Assets.instructions, 220, 420, 280,45);
        quit = new ImageButton(handler, Assets.quitButtons, 490, 420, 280,45);

        buttons = new ArrayList<>();
        buttons.add(start);
        //buttons.add(settings);
        buttons.add(select);
        buttons.add(instructions);
        buttons.add(quit);

    }

    @Override
    public void tick() {

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                switch(i) {
                    case 0: State.setState(handler.getGame().gameState); break;
                    //case 1: break;
                    case 1: State.setState(new UserInputState(handler)); break;
                    case 2: State.setState(new InstructionState(handler)); break;
                    case 3: System.exit(0); break;
                }
            }
        }

        y++;
        if (y > handler.getGame().getHeight()) {
            y = 0;
        }
    }



    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.startScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        g.drawImage(Assets.stars, 0, y, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.stars,0,y - handler.getHeight(), handler.getGame().getWidth(), handler.getGame().getHeight(),null);

        g.drawImage(Assets.title, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }

}
