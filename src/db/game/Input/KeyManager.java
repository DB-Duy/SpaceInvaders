package db.game.Input;

import db.game.Entities.Player;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    public String wordTyped = "";
    public boolean backspace = false;
    public boolean movable = false;
    public boolean isRightMoved, isLeftMoved;
    private Player player;

    public KeyManager() { }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void tick() {

    }

    public String getWordTyped() {
        return wordTyped;
    }



    @Override
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !backspace && wordTyped.length() > 0) {
            backspace = true;
            wordTyped = wordTyped.substring(0, wordTyped.length() - 1);
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        isRightMoved = false; isLeftMoved = false;
        if (movable) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //player.setX(player.getX() + 3);
                isRightMoved = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //player.setX(player.getX() - 3);
                isLeftMoved = true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            backspace = false;
        }
    }

    @Override
    public void keyTyped (KeyEvent e) {
        if (!backspace && e.getKeyChar()!= KeyEvent.VK_BACK_SPACE) {
            wordTyped += e.getKeyChar();
        }
    }
    public void resetWordTyped(){
        this.wordTyped = "";
    }


}

