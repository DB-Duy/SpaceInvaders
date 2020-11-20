package db.game.db.game.Input;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    public String wordTyped = "";


    public KeyManager() { }

    public void tick() {

    }

    public String getWordTyped() {
        return wordTyped;
    }


    @Override
    public void keyPressed (KeyEvent e) {

    }

    @Override
    public void keyReleased (KeyEvent e) {

    }

    @Override
    public void keyTyped (KeyEvent e) {
        wordTyped += e.getKeyChar();
        if (e.getKeyChar() == ' ') {
            wordTyped = "";
        }
    }
    public void resetWordTyped(){
        this.wordTyped = "";
    }


}

