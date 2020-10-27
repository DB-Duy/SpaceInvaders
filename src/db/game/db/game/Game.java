package db.game.db.game;

import db.game.db.game.display.Assets;
import db.game.db.game.display.Display;
import db.game.db.game.display.ImageLoader;

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

    public Game(String title, int width, int height){

        this.width=width;
        this.height=height;
        this.title=title;
        
    }
    private void init(){
        display = new Display(title, width, height);
        Assets.init();

    }
    private void tick(){

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
        g.drawImage(Assets.enemy4,50,50,null);
        g.drawImage(Assets.impact1,10,10,null);
        //draw end

        bs.show();
        g.dispose();
    }
    public void run(){
        init();
        while(running){
            tick();
            render();
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
