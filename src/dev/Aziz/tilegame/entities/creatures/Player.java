package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animation
    //Body
    private Animation animDown;
    private Animation animUp;
    private Animation animRight;
    private Animation animLeft;
    //Pants
    private Animation animPantsDown;
    private Animation animPantsUp;
    private Animation animPantsRight;
    private Animation animPantsLeft;



    //Attack timer
    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y){
        super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 35 / 2;
        bounds.y = 30 / 2;
        bounds.width = 64 / 2;
        bounds.height = 98 / 2;

        health = 1000;

        lastAttackTimer = System.currentTimeMillis();

        int playerSpeed = 40; //ms
        //Init of animations
        animDown = new Animation(playerSpeed, Assets.player_down);
        animUp = new Animation(playerSpeed, Assets.player_up);
        animRight = new Animation(playerSpeed, Assets.player_right);
        animLeft = new Animation(playerSpeed, Assets.player_left);
        animPantsDown = new Animation(playerSpeed, Assets.player_pants_down);
        animPantsUp = new Animation(playerSpeed, Assets.player_pants_up);
        animPantsRight = new Animation(playerSpeed, Assets.player_pants_right);
        animPantsLeft = new Animation(playerSpeed, Assets.player_pants_left);

        //Inventory
        inventory = new Inventory(handler);

    }

    @Override
    public void tick() {
        //Animation
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        animPantsDown.tick();
        animPantsUp.tick();
        animPantsRight.tick();
        animPantsLeft.tick();

        die();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();

    }



    private void checkAttacks(){

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        if(inventory.isActive()){
            return;
        }

        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 20; //px
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;                       // -arSize because we attack upwards
        } else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (inventory.isActive()) {
            return;
        }

        if(handler.getKeyManager().up){
            yMove = -speed;
        }
        if(handler.getKeyManager().down){
            yMove = speed;
        }
        if(handler.getKeyManager().left){
            xMove = -speed;
        }
        if(handler.getKeyManager().right){
            xMove = speed;
        }
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrameBody(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawImage(getCurrentAnimationFramePants(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);

    }

    public void postRender(Graphics g){
        inventory.render(g);
    }

    @Override
    public void die() {
        System.out.println("Player died!");
        if(health <= 0){
            setActive(false);

            //animation
            //change state -> GameOverState
        }
        System.out.println("Health: " + health);

    }

    private BufferedImage getCurrentAnimationFrameBody(){

        if(handler.getKeyManager().up){
            return animUp.getCurrentFrame();
        }
        if(handler.getKeyManager().down){
            return animDown.getCurrentFrame();
        }
        if(handler.getKeyManager().left){
            return animLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().right){
            return animRight.getCurrentFrame();
        }

        return animDown.getFrameAtIndex(1);
    }

    private BufferedImage getCurrentAnimationFramePants(){

        if(handler.getKeyManager().up){
            return animPantsUp.getCurrentFrame();
        }
        if(handler.getKeyManager().down){
            return animPantsDown.getCurrentFrame();
        }
        if(handler.getKeyManager().left){
            return animPantsLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().right){
            return animPantsRight.getCurrentFrame();
        }

        return animPantsDown.getFrameAtIndex(1);
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
