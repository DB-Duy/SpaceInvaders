package db.game.db.game;

import db.game.db.game.display.Assets;
import db.game.db.game.display.Display;
import db.game.db.game.display.ImageLoader;
import db.game.db.game.states.GameState;
import db.game.db.game.states.MenuState;
import db.game.db.game.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    private Display display;
    public int height, width;
    public String title;

    private boolean running=false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;

    public Game(String title, int width, int height){

        this.width=width;
        this.height=height;
        this.title=title;
        
    }
    private void init(){
        display = new Display(title, width, height);
        Assets.init();

        gameState=new GameState();
        menuState=new MenuState();
        State.setState(gameState);

    }
    private void tick(){
        if(State.getState()!=null){
            State.getState().tick();
        }
    }
    private void render(){
        bs=display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g=bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        //draw start
        if(State.getState()!=null){
            State.getState().render(g);
        }
        //draw end

        bs.show();
        g.dispose();
    }
    public void run(){
        init();

        int fps=60;
        double timePerTick=1000000000/fps;
        double delta=0;
        long now;
        long lastTime=System.nanoTime();

        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            lastTime=now;

            if(delta>=1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    public synchronized void start(){
        if(running){
            return;
        }
        running=true;
        thread=new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running){
            return;
        }
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
