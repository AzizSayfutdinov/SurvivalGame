package dev.Aziz.tilegame.entities.movingObjects;

import dev.Aziz.tilegame.Handler;

import java.awt.*;
import java.util.ArrayList;

public class MovingObjectsManager {

    private Handler handler;
    private ArrayList<MovingObject> fireBalls;
    private ArrayList<Rectangle> fireBallBounds;

    public MovingObjectsManager(Handler handler){
        this.handler = handler;
    }

}
