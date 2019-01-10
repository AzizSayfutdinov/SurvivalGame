package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;

import java.awt.*;

public abstract class State {

    //State Manager
    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }


    //Class

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);    //we need the magical paint brush from the Game to be able to draw



}
