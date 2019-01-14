package dev.Aziz.tilegame.entities.movingObjects;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class FireBall extends Entity {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;


    private int speed = 10;


    public FireBall(Handler handler) {
        super(handler, handler.getWorld().getEntityManager().getPlayer().getX(), handler.getWorld().getEntityManager().getPlayer().getY(),Tile.TILEWIDTH / 2, Tile.TILEHEIGHT / 2);
        setDirection();

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;

    }

    @Override
    public void tick() {
        move();
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

    private void move(){

        if(up){
            y -= speed;
        }
        if(down){
            y += speed;
        }
        if(left){
            x -= speed;
        }
        if(right){
            x += speed;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height);

    }

    @Override
    public void die() {

    }
}
