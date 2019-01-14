package dev.Aziz.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right, esc;
    public boolean attackUp, attackDown, attackLeft, attackRight;
    public boolean shoot;

    public KeyManager(){
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];


    }

    public void tick(){

        for (int i = 0; i < keys.length; i++){
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;

            }
        }

        if(keyJustPressed(KeyEvent.VK_E)){
            System.out.println("E just pressed");
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        attackDown = keys[KeyEvent.VK_DOWN];
        attackUp = keys[KeyEvent.VK_UP];
        attackLeft = keys[KeyEvent.VK_LEFT];
        attackRight = keys[KeyEvent.VK_RIGHT];

        esc = keys[KeyEvent.VK_ESCAPE];

        shoot = keys[KeyEvent.VK_SPACE];

    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length){
            return false;
        }
        return justPressed[keyCode];
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
            return;
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
