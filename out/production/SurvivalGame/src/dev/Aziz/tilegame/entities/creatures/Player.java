package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.inventory.Inventory;
import dev.Aziz.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //AttackBounds

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
    //Sword
    private Animation animSwordDown;
    private Animation animSwordUp;
    private Animation animSwordRight;
    private Animation animSwordLeft;
    //Attack
    private Animation animAttackDown;
    private Animation animAttackUp;
    private Animation animAttackRight;
    private Animation animAttackLeft;


    private int points = 0;

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

        health = 10;

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

        animSwordDown = new Animation(playerSpeed, Assets.player_sword_down);
        animSwordUp = new Animation(playerSpeed, Assets.player_sword_up);
        animSwordRight = new Animation(playerSpeed, Assets.player_sword_right);
        animSwordLeft = new Animation(playerSpeed, Assets.player_sword_left);

        animAttackDown = new Animation(playerSpeed, Assets.player_down_attacking);
        animAttackUp = new Animation(playerSpeed, Assets.player_up_attacking);
        animAttackRight = new Animation(playerSpeed, Assets.player_right_attacking);
        animAttackLeft = new Animation(playerSpeed, Assets.player_left_attacking);

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
        animSwordDown.tick();
        animSwordUp.tick();
        animSwordLeft.tick();
        animSwordRight.tick();
        animAttackLeft.tick();
        animAttackRight.tick();
        animAttackUp.tick();
        animAttackDown.tick();

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

        if(handler.getKeyManager().attackUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;                       // -arSize because we attack upwards
        } else if(handler.getKeyManager().attackDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if(handler.getKeyManager().attackLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if(handler.getKeyManager().attackRight){
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
                e.hurt(5);      // amt = amount of damage
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
        g.setColor(Color.BLUE);
        g.drawImage(getCurrentAnimationFrameBody(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawImage(getCurrentAnimationFrameSword(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawImage(getCurrentAnimationFramePants(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);

        // Health
        g.setColor(Color.BLACK);
        g.drawRect(handler.getGame().getWidth() - 110, 20, 100, 15);

        if(health > 70)
            g.setColor(Color.GREEN);
        else if(health > 20)
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.RED);
        g.fillRect(handler.getGame().getWidth() - 110, 20,  health, 15);

    }

    public void postRender(Graphics g){
        inventory.render(g);
    }

    @Override
    public void die() {

        if(health <= 0){
            setActive(false);

            State.setState(handler.getGame().gameOverState);

            //animation
            //change state -> GameOverState
        }

    }

    private BufferedImage getCurrentAnimationFrameBody(){

        if(handler.getKeyManager().up){
            return animUp.getCurrentFrame();
        }
        if(handler.getKeyManager().attackUp) {
            return animAttackUp.getCurrentFrame();
        }
        if(handler.getKeyManager().down){
            return animDown.getCurrentFrame();
        }
        if(handler.getKeyManager().attackDown){
            return animAttackDown.getCurrentFrame();
        }
        if(handler.getKeyManager().left){
            return animLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().attackLeft){
            return animAttackLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().right){
            return animRight.getCurrentFrame();
        }
        if(handler.getKeyManager().attackRight){
            return animAttackRight.getCurrentFrame();
        }

        return animDown.getFrameAtIndex(0);
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

    private BufferedImage getCurrentAnimationFrameSword(){

        if(handler.getKeyManager().attackUp){
            return animSwordUp.getCurrentFrame();
        }
        if(handler.getKeyManager().attackDown){
            return animSwordDown.getCurrentFrame();
        }
        if(handler.getKeyManager().attackLeft){
            return animSwordLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().attackRight){
            return animSwordDown.getCurrentFrame();
        }

        return getCurrentAnimationFrameBody();
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}