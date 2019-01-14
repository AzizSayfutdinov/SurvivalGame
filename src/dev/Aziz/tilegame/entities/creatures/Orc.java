package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Orc extends Enemy {

    private static final int ORC_HEALTH = 20;

    //Animation
    //Move
    private Animation animDown;
    private Animation animUp;
    private Animation animRight;
    private Animation animLeft;
    //Attack
    private Animation animAttackDown;
    private Animation animAttackUp;
    private Animation animAttackRight;
    private Animation animAttackLeft;

    public Orc(Handler handler, float x, float y) {
        super(handler, x, y);

        health = ORC_HEALTH;

        animDown = new Animation(enemyAnimSpeed, Assets.orc_down);
        animUp = new Animation(enemyAnimSpeed, Assets.orc_up);
        animRight = new Animation(enemyAnimSpeed, Assets.orc_right);
        animLeft = new Animation(enemyAnimSpeed, Assets.orc_left);
        animAttackDown = new Animation(enemyAnimSpeed, Assets.orc_down_attacking);
        animAttackUp = new Animation(enemyAnimSpeed, Assets.orc_up_attacking);
        animAttackRight = new Animation(enemyAnimSpeed, Assets.orc_right_attacking);
        animAttackLeft = new Animation(enemyAnimSpeed, Assets.orc_left_attacking);


    }

    public void tick(){

        super.tick();

        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        animAttackDown.tick();
        animAttackUp.tick();
        animAttackRight.tick();
        animAttackLeft.tick();

    }

    public void render(Graphics g){

        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width, height, null);

        //Health
        g.setColor(Color.BLACK);
        g.drawRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()) - 10, getWidth(), 10);
        if(health > 16)
            g.setColor(Color.GREEN);
        else if(health > 9)
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.RED);
        g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()) - 10, getWidth() / ORC_HEALTH * health, 10);

        g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
        g.setColor(Color.YELLOW);
        g.drawRect((int)((x - handler.getGameCamera().getxOffset()) + playerBound.x),(int)(y - handler.getGameCamera().getyOffset()) + playerBound.y, playerBound.width, playerBound.height);
        g.setColor(Color.RED);
        g.drawRect((int)((x - handler.getGameCamera().getxOffset()) + attackBounds.x),(int)(y - handler.getGameCamera().getyOffset()) + attackBounds.y, attackBounds.width, attackBounds.height);

    }

    private BufferedImage getCurrentAnimationFrame(){

        if(moveRight && attacking){
            return animAttackRight.getCurrentFrame();
        }

        if(moveUp && attacking){
            return animAttackUp.getCurrentFrame();
        }

        if(moveLeft && attacking){
            return animAttackLeft.getCurrentFrame();
        }

        if(moveDown && attacking){
            return animAttackDown.getCurrentFrame();
        }

        if(moveRight){
            return animRight.getCurrentFrame();
        }

        if(moveUp){
            return animUp.getCurrentFrame();
        }

        if(moveLeft){
            return animLeft.getCurrentFrame();
        }

        if(moveDown){
            return animDown.getCurrentFrame();
        }

        return animDown.getFrameAtIndex(1);

    }
}
