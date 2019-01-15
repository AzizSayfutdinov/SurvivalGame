package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;

import java.awt.*;



public class Enemy extends Creature {

    protected Rectangle attackBounds;

    protected Rectangle playerBound;

    private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;

    protected int enemyAnimSpeed = 100;

    protected boolean moveUp = false, moveDown = false, moveRight = false, moveLeft = false;
    protected boolean attacking = false;

    protected int speed = 0;




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

        // used for proper animation
        if(attackBounds.intersects(playerBound)){
            attacking = true;
        } else
            attacking = false;

        if( attackTimer < attackCooldown){
            return;
        }

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

    }

    @Override
    public void render(Graphics g) {

    }


    @Override
    public void die() {

    }



    public Rectangle getAttackBounds() {
        return attackBounds;
    }

    public void setAttackBounds(Rectangle attackBounds) {
        this.attackBounds = attackBounds;
    }

}
