package db.game.States;

import db.game.Display.Assets;
import db.game.Display.ImageLoader;
import db.game.Main.Game;
import db.game.Main.Handler;
import db.game.UI.ClickListener;
import db.game.UI.UIImageButton;
import db.game.UI.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    private int y = 0;


    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(375, 320, 240, 40, Assets.startButtons, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(375, 380, 240, 40, Assets.settings, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
            }
        }));

        uiManager.addObject(new UIImageButton(375, 440, 240, 40, Assets.instructions, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                //State.setState(handler.getGame().instructionState);
            }
        }));

        uiManager.addObject(new UIImageButton(375, 500, 240, 40, Assets.quitButtons, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                System.exit(0);
            }
        }));



    }

    @Override
    public void tick() {
        uiManager.tick();
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
        uiManager.render(g);
    }

}
