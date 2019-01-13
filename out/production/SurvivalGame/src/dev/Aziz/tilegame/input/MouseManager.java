package dev.Aziz.tilegame.input;

import dev.Aziz.tilegame.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;
    private boolean stateActive = true;

    public MouseManager(){

    }

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    //Helper Methods
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

    public boolean isStateActive() {
        return stateActive;
    }

    public void setStateActive(boolean stateActiive) {
        this.stateActive = stateActiive;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(stateActive) {

            if (e.getButton() == MouseEvent.BUTTON1) {        //BUTTON1 = left button
                leftPressed = true;
            } else if (e.getButton() == MouseEvent.BUTTON3) {        //BUTTON3 = right button
                rightPressed = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(stateActive) {

            if (e.getButton() == MouseEvent.BUTTON1) {        //BUTTON1 = left button
                leftPressed = false;
            } else if (e.getButton() == MouseEvent.BUTTON3) {        //BUTTON3 = right button
                rightPressed = false;
            }

            if (uiManager != null) {
                uiManager.onMouseRelease(e);
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null){
            uiManager.onMouseMove(e);
        }
    }
}
