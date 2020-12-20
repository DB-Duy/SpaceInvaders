package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.util.ArrayList;

public class DeathState extends State {


    private ImageButton tryAgain, quit;
    private ArrayList<ImageButton> buttons;
    private int y = 0;

    public DeathState(Handler handler) {
        super(handler);

        tryAgain = new ImageButton(handler, Assets.tryAgain, 290, 320, 240, 40);
        quit = new ImageButton(handler, Assets.quitButtons, 480, 320, 240, 40);

        buttons = new ArrayList<>();
        buttons.add(tryAgain);
        buttons.add(quit);
    }

    @Override
    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                switch (i) {
                    case 0:
                        State.setState(new GameState(handler));
                        break;
                    case 1:
                        System.exit(0);
                        break;
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
        g.drawImage(Assets.gameOverScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        g.drawImage(Assets.stars, 0, y, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawImage(Assets.stars,0,y - handler.getHeight(), handler.getGame().getWidth(), handler.getGame().getHeight(),null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }
}
