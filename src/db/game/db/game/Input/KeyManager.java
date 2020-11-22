package db.game.db.game.Input;

import java.awt.event.*;
import java.lang.StringBuilder;

public class KeyManager implements KeyListener {

    public String wordTyped = "";
    private StringBuilder sb;

    public KeyManager() {
        sb = new StringBuilder();
    }

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
        else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            int length = wordTyped.length();
            sb.append(wordTyped);
            wordTyped = sb.deleteCharAt(length - 1).toString();

        }
    }

    public void resetWordTyped(){
        this.wordTyped = "";
        this.sb.setLength(0);
    }


}

