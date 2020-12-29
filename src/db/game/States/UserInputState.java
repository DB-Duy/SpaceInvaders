package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInputState extends State {

    private ImageButton back, arrow;
    private ArrayList<ImageButton> buttons;
    private ArrayList<String> userNames;
    private File file;
    private Scanner scanner;
    private FileWriter fileWriter;


    public UserInputState(Handler handler) {
        super(handler);
        file = new File("leaderboard.txt");


        buttons = new ArrayList<>();
        userNames = new ArrayList<>();
        back = new ImageButton(handler, Assets.back, 10, 530, 250, 45);
        arrow = new ImageButton(handler, Assets.arrowButtons, 0, 0, 0, 0);

        buttons.add(back);

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                userNames.add(scanner.nextLine());
            }
        } catch (FileNotFoundException a) {
            a.printStackTrace();
        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                State.setState(new MenuState(handler));
            }
        }

        for (int i = 0; i < userNames.size(); i++) {
            Rectangle bounds = new Rectangle(440, 220 + i*90, 100, 50);
            if (bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) {
                arrow.setX(370);
                arrow.setY(210 + i*90);
                arrow.setWidth(70);
                arrow.setHeight(45);
            }
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.selectScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
        arrow.render(g);

        g.setColor(new Color(241, 217, 27));
        g.setFont(new Font("Minecraft", Font.PLAIN, 50));
        for (int i = 0; i < userNames.size(); i++) {
            g.drawString(userNames.get(i), 460, 250 + i*90);
        }
    }
}
