package db.game.Input;

import java.awt.*;
import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;

    public MouseManager(){

    }

    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {



    }

    public boolean isClicked() {
        return true;
    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

}
