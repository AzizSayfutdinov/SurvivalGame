package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Creature {

    //Animation
    private Animation animDown;
    private Animation animUp;
    private Animation animRight;
    private Animation animLeft;

    private Rectangle attackBounds;

    private Rectangle playerBound;

    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

    private boolean moveUp = false, moveDown = false, moveRight = false, moveLeft = false;

    private int speed = 4;




    public Enemy(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 35 / 2;
        bounds.y = 30 / 2;
        bounds.width = 64 / 2;
        bounds.height = 98 / 2;

        attackBounds = new Rectangle();

        lastAttackTimer = System.currentTimeMillis();

        attackBounds.x = 5;
        attackBounds.y = 5;
        attackBounds.width = width;
        attackBounds.height = height;

        int enemyAnimSpeed = 100;

        animDown = new Animation(enemyAnimSpeed, Assets.enemy_down);
        animUp = new Animation(enemyAnimSpeed, Assets.enemy_up);
        animRight = new Animation(enemyAnimSpeed, Assets.enemy_right);
        animLeft = new Animation(enemyAnimSpeed, Assets.enemy_left);

        yMove = speed;
        xMove = speed;

    }

    public void move(){

        chase();
    }

    public void chase(){

        float dx = x - handler.getWorld().getEntityManager().getPlayer().getX();
        float dy = y - handler.getWorld().getEntityManager().getPlayer().getY();
        System.out.println("dx: " + dx);
        System.out.println("dy: " + dy);

        if(Math.abs(dx) > Math.abs(dy)){


            if(dx < 0){
                xMove = speed;

            } else if(dx > 0) {
                xMove = -speed;
            }


            if(!checkEntityCollisions(xMove, 0f)) {
                moveX();
                if(xMove > 0) {
                    moveDown = false;
                    moveUp = false;
                    moveRight = true;
                    moveLeft = false;
                } else {
                    moveDown = false;
                    moveUp = false;
                    moveRight = false;
                    moveLeft = true;
                }
            }

            else {
                if(!checkEntityCollisions(0f, yMove)) {
                    if (!attackBounds.intersects(playerBound)) {            //unclear why attackBounds work XD
                        moveY();
                        if(yMove > 0) {
                            moveDown = true;
                            moveUp = false;
                            moveRight = false;
                            moveLeft = false;
                        } else {
                            moveDown = false;
                            moveUp = true;
                            moveRight = false;
                            moveLeft = false;
                        }

                    }
                }
            }

        } else {

            if(dy < 0){
                yMove = speed;
            } else if(dy > 0){
                yMove = -speed;
            }



            if(!checkEntityCollisions(0f, yMove)) {
                moveY();
                if(yMove > 0) {
                    moveDown = true;
                    moveUp = false;
                    moveRight = false;
                    moveLeft = false;
                } else {
                    moveDown = false;
                    moveUp = true;
                    moveRight = false;
                    moveLeft = false;
                }
            }
            else{
                if(!checkEntityCollisions(xMove, 0f)){
                    if(!attackBounds.intersects(playerBound)){
                        moveX();
                        if(xMove > 0) {
                            moveDown = false;
                            moveUp = false;
                            moveRight = true;
                            moveLeft = false;
                        } else {
                            moveDown = false;
                            moveUp = false;
                            moveRight = false;
                            moveLeft = true;
                        }
                    }
                }
            }

        }
    }

    //TODO: extend, so that the enemy can walk past solid tiles

    @Override
    public void moveY(){
        super.moveY();
    }

    @Override
    public void moveX(){
        super.moveX();
    }


    public void attack(){

        playerBound = handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(-x ,-y );

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;


        if(attackBounds.intersects(playerBound)){
            handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() - 1);
            System.out.println("attack bound intersects collision bound of player");
        }

        attackTimer = 0;

    }




    @Override
    public void tick() {

        die();
        move();
        attack();
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width, height, null);
        g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
        g.setColor(Color.YELLOW);
        g.drawRect((int)((x - handler.getGameCamera().getxOffset()) + playerBound.x),(int)(y - handler.getGameCamera().getyOffset()) + playerBound.y, playerBound.width, playerBound.height);
        g.setColor(Color.RED);
        g.drawRect((int)((x - handler.getGameCamera().getxOffset()) + attackBounds.x),(int)(y - handler.getGameCamera().getyOffset()) + attackBounds.y, attackBounds.width, attackBounds.height);

    }


    @Override
    public void die() {
        //never
    }

    private BufferedImage getCurrentAnimationFrame(){

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


    public Rectangle getAttackBounds() {
        return attackBounds;
    }

    public void setAttackBounds(Rectangle attackBounds) {
        this.attackBounds = attackBounds;
    }

}
