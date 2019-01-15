package dev.Aziz.tilegame.entities.movingObjects;

import dev.Aziz.tilegame.Handler;

import java.awt.*;
import java.util.ArrayList;

public class MovingObjectsManager {

    private Handler handler;
    private ArrayList<MovingObject> movingObjects;

    public MovingObjectsManager(Handler handler){
        this.handler = handler;
        movingObjects = new ArrayList<>();

    }

    public void tick(){

        for(MovingObject m: movingObjects){
            m.tick();
        }

    }

    public void render(Graphics g){

        for(MovingObject m: movingObjects){
            m.render(g);
        }

    }

    public void addObject(MovingObject m){
        movingObjects.add(m);
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    public void setMovingObjects(ArrayList<MovingObject> movingObjects) {
        this.movingObjects = movingObjects;
    }


}
