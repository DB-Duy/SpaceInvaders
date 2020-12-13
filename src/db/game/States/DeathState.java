package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ClickListener;
import db.game.UI.UIImageButton;
import db.game.UI.UIManager;

import java.awt.*;

public class DeathState extends State {

    private UIManager uiManager;

    public DeathState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImageButton(290, 320, 240, 40, Assets.tryAgain, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(480, 320, 240, 40, Assets.quitButtons, new ClickListener() {
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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.gameOverScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        uiManager.render(g);
    }
}
