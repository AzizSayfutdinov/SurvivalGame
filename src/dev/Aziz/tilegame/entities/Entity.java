package dev.Aziz.tilegame.entities;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.creatures.Orc;
import dev.Aziz.tilegame.entities.creatures.Skeleton;

import javax.net.ssl.SSLKeyException;
import java.awt.*;

public abstract class Entity {

    public static final int DEFAULT_HEALTH = 10;

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected boolean active = true;        // = visible. if not active, the entity will be deleted!
    protected Rectangle bounds;
    protected int health;



    public Entity(Handler handler, float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0,0, width, height);
    }

    public static int getDefaultHealth() {
        return DEFAULT_HEALTH;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }



    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(int amt){
        health -= amt;
        if(health <= 0){
            setActive(false);     //removing from game
            die();
            if(this instanceof Orc)
                handler.getWorld().getEntityManager().getPlayer().setPoints(handler.getWorld().getEntityManager().getPlayer().getPoints() + 10);
            if(this instanceof Skeleton)
                handler.getWorld().getEntityManager().getPlayer().setPoints(handler.getWorld().getEntityManager().getPlayer().getPoints() + 4);
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }





}
