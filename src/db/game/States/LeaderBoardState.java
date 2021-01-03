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
import java.util.*;

public class LeaderBoardState extends State {

    private File file;
    private FileWriter fileWriter;
    public static ArrayList<String> userNames = Leaderboard.getLeaderboardInstance().getUserNames();
    public static ArrayList<Integer> scores = Leaderboard.getLeaderboardInstance().scores;
    private ImageButton back;
    private Scanner scanner;
    private Leaderboard board;

    public LeaderBoardState(Handler handler) {
        super(handler);
        back = new ImageButton(handler, Assets.back, 10, 500, 240, 40);

        board = Leaderboard.getLeaderboardInstance();

        if (handler.getGame().playerScore > board.getPlayerScore(handler.getGame().playerName)) {
            board.updateScore(handler.getGame().playerName, handler.getGame().playerScore);
        }

        System.out.println(board.getLeaderboard().toString());

    }

    @Override
    public void tick() {
        if (back.isHovering() && handler.getMouseManager().isLeftPressed()) {
            State.setState(new DeathState(handler));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.leaderboardScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        back.render(g);

        g.setColor(new Color(241, 217, 27));
        g.setFont(new Font("Minecraft", Font.PLAIN, 50));

        for (int i = 0; i < userNames.size(); i++) {
            g.drawString(userNames.get(i), 350, 250 + i*90);
            g.drawString(scores.get(i).toString(), 580, 250 + i*90);
        }



    }
}
