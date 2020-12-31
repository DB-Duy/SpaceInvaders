package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.util.ArrayList;

public class CreateUserState extends State {

    private ImageButton back;
    private ArrayList<ImageButton> buttons;

    public CreateUserState(Handler handler) {
        super(handler);

        buttons = new ArrayList<>();
        back = new ImageButton(handler, Assets.back, 10, 530, 250, 45);

        buttons.add(back);
    }

    @Override
    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                State.setState(new SelectState(handler));
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enterScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }
}
