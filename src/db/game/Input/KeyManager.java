package db.game.Input;

import db.game.Entities.Player;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    public String wordTyped = "";
    public boolean typeable = true;
    public boolean backspace = false;
    public boolean movable = false;
    public int increment;
    private Player player;

    public KeyManager() { }


    public void tick() {

    }

    public String getWordTyped() {
        return wordTyped;
    }



    @Override
    public void keyPressed (KeyEvent e) {
        if (movable) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) { ;
                increment = 5;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                increment = -5;
            }
        }

        if (typeable && e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !backspace && wordTyped.length() > 0) {
            backspace = true;
            wordTyped = wordTyped.substring(0, wordTyped.length() - 1);
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            backspace = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            increment = 0;
        }
    }

    @Override
    public void keyTyped (KeyEvent e) {
        if (typeable && !backspace && e.getKeyChar()!= KeyEvent.VK_BACK_SPACE) {
            wordTyped += e.getKeyChar();
        }
    }
    public void resetWordTyped(){
        this.wordTyped = "";
    }


}

