package dev.Aziz.tilegame.entities.movingObjects;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FireBall extends MovingObject {

    private int speed = 10;
    private int animSpeed = 70;

    private Animation animDown;
    private Animation animUp;
    private Animation animRight;
    private Animation animLeft;

    public FireBall(Handler handler) {
        super(handler, handler.getWorld().getEntityManager().getPlayer().getX() - 20, handler.getWorld().getEntityManager().getPlayer().getY() + 10,Tile.TILEWIDTH / 2, Tile.TILEHEIGHT / 2);

        bounds.x = 34;
        bounds.y = 30;
        bounds.width = 2 * width;
        bounds.height = 2 * height;

        animDown = new Animation(animSpeed, Assets.fireball_down);
        animUp = new Animation(animSpeed, Assets.fireball_up);
        animRight = new Animation(animSpeed, Assets.fireball_right);
        animLeft = new Animation(animSpeed, Assets.fireball_left);

    }

    @Override
    public void tick() {
        move();
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

        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 3, null);

        //g.setColor(Color.red);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height);
        //g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);

    }

    @Override
    public void die() {

    }

    private BufferedImage getCurrentAnimationFrame(){

        if(right){
            return animRight.getCurrentFrame();
        }

        if(up){
            return animUp.getCurrentFrame();
        }

        if(left){
            return animLeft.getCurrentFrame();
        }

        if(down){
            return animDown.getCurrentFrame();
        }

        return animDown.getFrameAtIndex(1);

    }
}
