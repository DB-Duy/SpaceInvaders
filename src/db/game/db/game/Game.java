package db.game.db.game;

import db.game.db.game.Input.KeyManager;
import db.game.db.game.Display.Assets;
import db.game.db.game.Display.Display;
import db.game.db.game.Display.ImageLoader;
import db.game.db.game.states.GameState;
import db.game.db.game.states.MenuState;
import db.game.db.game.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    public int height, width;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private State gameState;
    private State menuState;

    private KeyManager keyManager;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        keyManager = new KeyManager();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }


    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
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
        //g.fillRect(0,0,width,height);
        g.drawImage(ImageLoader.loadImage("/resources/background.png"),0, 0, null);

        // draw start
        if (gameState != null) {
            State.getState().render(g);
        }

        //draw end
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