package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserState extends State {

    private ImageButton back, start;
    private ArrayList<ImageButton> buttons;
    private File file;
    private FileWriter fileWriter;

    public CreateUserState(Handler handler) {
        super(handler);
        file = new File(".//res//leaderboard//leaderboard2.txt");

        buttons = new ArrayList<>();
        back = new ImageButton(handler, Assets.back, 10, 530, 250, 45);
        start = new ImageButton(handler, Assets.startButtons, 640, 275, 270, 55);

        buttons.add(back);
        buttons.add(start);
    }

    @Override
    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                if (i == 0) {
                    State.setState(new SelectState(handler));
                }
                else {
                    try {
                        fileWriter = new FileWriter(file, true);
                        fileWriter.write("\n" + handler.getKeyManager().getWordTyped());
                        fileWriter.close();
                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                    handler.getKeyManager().resetWordTyped();
                    State.setState(new GameState(handler));
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enterScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }

        g.setColor(new Color(241, 217, 27));
        g.setFont(new Font("Minecraft", Font.PLAIN, 45));
        g.drawString(handler.getKeyManager().getWordTyped(), 170,320);

    }
}
