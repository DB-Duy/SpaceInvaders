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


        /*file = new File(".//res//leaderboard//leaderboard2.txt");
        userNames = new ArrayList<>();
        scores = new ArrayList<>();

        back = new ImageButton(handler, Assets.back, 10, 500, 240, 40);

        int lineNum = 0, num = 0;

        try {
            boolean isName = false;
            scanner = new Scanner(file);
            fileWriter = new FileWriter(file, false);
            while (scanner.hasNextLine()) {
                lineNum++;
                if (scanner.nextLine().equals(handler.getGame().playerName)) {
                    isName = true;
                    num = lineNum;
                    System.out.println(num);
                }
                if (isName && lineNum == num + 1) {
                    System.out.println(lineNum);
                    fileWriter.write(String.valueOf(handler.getGame().playerScore));
                }
                else {
                    continue;
                }
            }
            fileWriter.close();
        } catch (IOException b) {
            b.printStackTrace();
        }


        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lineNum++;
                if (lineNum % 2 != 0) {
                    scores.add(scanner.nextLine());
                }
                else {
                    userNames.add(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException a) {
            a.printStackTrace();
        }*/
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
