package dev.Aziz.tilegame.entities.movingObjects;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;

import java.awt.*;

public abstract class MovingObject extends Entity {

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;



    public MovingObject(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        setDirection();
    }

    private void setDirection(){

        if(handler.getWorld().getEntityManager().getPlayer().getLastAnimation().equals(handler.getWorld().getEntityManager().getPlayer().getAnimDown())){
            down = true;
        }
        if(handler.getWorld().getEntityManager().getPlayer().getLastAnimation().equals(handler.getWorld().getEntityManager().getPlayer().getAnimUp())){
            up = true;
        }
        if(handler.getWorld().getEntityManager().getPlayer().getLastAnimation().equals(handler.getWorld().getEntityManager().getPlayer().getAnimLeft())){
            left = true;
        }
        if(handler.getWorld().getEntityManager().getPlayer().getLastAnimation().equals(handler.getWorld().getEntityManager().getPlayer().getAnimRight())){
            right = true;
        }

    }

}
