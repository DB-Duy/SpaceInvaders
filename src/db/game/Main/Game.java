package db.game.Main;

import db.game.FunctionManagement.Leaderboard;
import db.game.Input.KeyManager;
import db.game.Display.Assets;
import db.game.Display.Display;
import db.game.Display.ImageLoader;
import db.game.Input.MouseManager;
import db.game.States.*;
import db.game.Sounds.Sound;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    public int height, width;
    public String title;

    public String playerName;
    public int playerScore;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public State gameState;
    public State menuState;

    private Handler handler;

    private KeyManager keyManager;
    private MouseManager mouseManager;
    private Leaderboard board;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        board = Leaderboard.getLeaderboardInstance();

        State.setState(menuState);
    }

    public Display getDisplay() {
        return this.display;
    }


    private void tick() {

        keyManager.tick();

        if (gameState != null) {
            State.getState().tick();
        }

    }


    private void render() {

        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);


        if (gameState != null) {
            State.getState().render(g);
        }


        bs.show();
        g.dispose();
    }


    public void run() {
        init();

        int fps = 60, ticks = 0;
        double timePerTick = 1E9 / fps;
        double delta = 0;
        long now, lastTime = System.nanoTime(), timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1E9) {
                System.out.println(ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}