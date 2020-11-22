package db.game.db.game.Input;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    public String wordTyped = "";
    public boolean backspace=false;


    public KeyManager() { }

    public void tick() {

    }

    public String getWordTyped() {
        return wordTyped;
    }


    @Override
    public void keyPressed (KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE && backspace==false && wordTyped.length()>0){
            backspace=true;
            wordTyped=wordTyped.substring(0,wordTyped.length()-1);
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            backspace=false;
        }
    }

    @Override
    public void keyTyped (KeyEvent e) {
        if(backspace==false && e.getKeyChar()!=KeyEvent.VK_BACK_SPACE) {
            wordTyped += e.getKeyChar();
            if (e.getKeyChar() == ' ') {
                wordTyped = "";
            }
        }
    }
    public void resetWordTyped(){
        this.wordTyped = "";
    }


}

