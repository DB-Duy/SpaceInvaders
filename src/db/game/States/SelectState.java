package db.game.States;

import db.game.Display.Assets;
import db.game.FunctionManagement.Leaderboard;
import db.game.Main.Handler;
import db.game.UI.ImageButton;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectState extends State {

    private ImageButton back, arrow, createNew;
    private ArrayList<ImageButton> buttons;

    private File file;
    private Scanner scanner;
    private FileWriter fileWriter;
    private Leaderboard board;
    public ArrayList<String> userNames;
    public ArrayList<Integer> scores;

    public SelectState(Handler handler) {
        super(handler);
        //file = new File(".//res//leaderboard//leaderboard2.txt");
        setLastState(false);
        board = Leaderboard.getLeaderboardInstance();

        buttons = new ArrayList<>();
        userNames = new ArrayList<>();
        scores = new ArrayList<>();

        back = new ImageButton(handler, Assets.back, 10, 530, 250, 45);
        arrow = new ImageButton(handler, Assets.arrowButtons, 0, 0, 0, 0);
        createNew = new ImageButton(handler, Assets.createButtons, 690, 530, 280, 40);

        buttons.add(back);
        buttons.add(createNew);

        for(Object name : board.getLeaderboard().keySet()){
            userNames.add((String) name);
        }
        for(Object score: board.getLeaderboard().values()){
            scores.add((Integer) score);
        }

        /*int num = 0;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                num++;
                if (num % 2 != 0) {
                    userNames.add(scanner.nextLine());
                }
                else {
                    scores.add(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException a) {
            a.printStackTrace();
        }*/
    }

    @Override
    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isHovering() && handler.getMouseManager().isLeftPressed()) {
                if (i == 0) {
                    State.setState(new MenuState(handler));
                }
                else {
                    State.setState(new CreateUserState(handler));
                }
            }
        }

        for (int i = 0; i < userNames.size(); i++) {
            Rectangle bounds = new Rectangle(440, 220 + i*90, 100, 50);
            if (bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) {
                arrow.setX(370);
                arrow.setY(210 + i*90);
                arrow.setWidth(70);
                arrow.setHeight(45);
                if (handler.getMouseManager().isLeftPressed()) {
                    handler.getGame().playerName = userNames.get(i);
                    System.out.println(handler.getGame().playerName);
                    State.setState(new GameState(handler));
                }
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
