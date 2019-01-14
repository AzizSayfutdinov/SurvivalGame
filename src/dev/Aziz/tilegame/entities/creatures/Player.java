package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.inventory.Inventory;
import dev.Aziz.tilegame.sounds.SoundLoader;
import dev.Aziz.tilegame.states.State;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //AttackBounds

    //Sound
    SoundLoader loader = new SoundLoader();
    Clip c;


    //Animation
    //Body
    private Animation animDown;
    private Animation animUp;
    private Animation animRight;
    private Animation animLeft;

    //Attack
    private Animation animAttackDown;
    private Animation animAttackUp;
    private Animation animAttackRight;
    private Animation animAttackLeft;

    private Animation lastAnimation;


    private int points = 0;

    //Attack timer
    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y){
        super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 20;
        bounds.y = 25;
        bounds.width = 23;
        bounds.height = 30;

        loader = new SoundLoader();
        //c = loader.loadSound("res/sounds/stepdirt_1.wav");


        health = 100;

        lastAttackTimer = System.currentTimeMillis();

        int playerSpeed = 40; //ms
        //Init of animations
        animDown = new Animation(playerSpeed, Assets.player_down);
        animUp = new Animation(playerSpeed, Assets.player_up);
        animRight = new Animation(playerSpeed, Assets.player_right);
        animLeft = new Animation(playerSpeed, Assets.player_left);

        animAttackDown = new Animation(playerSpeed, Assets.player_down_attacking);
        animAttackUp = new Animation(playerSpeed, Assets.player_up_attacking);
        animAttackRight = new Animation(playerSpeed, Assets.player_right_attacking);
        animAttackLeft = new Animation(playerSpeed, Assets.player_left_attacking);

        lastAnimation = animDown;

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

        animAttackLeft.tick();
        animAttackRight.tick();
        animAttackUp.tick();
        animAttackDown.tick();

        die();
        //Movement
        getInput();
        move();
        playWalkSound();
        //SoundLoader.playSound(Assets.testSound);
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();

    }


    private void playWalkSound(){

        

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

        // Bounds
        //g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);

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

    private BufferedImage getCurrentAnimationFrameBody(){       //TODO: remember direction to stand still

        if(handler.getKeyManager().up){
            lastAnimation = animUp;
            return animUp.getCurrentFrame();
        }
        if(handler.getKeyManager().attackUp) {
            lastAnimation = animUp;
            return animAttackUp.getCurrentFrame();
        }
        if(handler.getKeyManager().down){
            lastAnimation = animDown;
            return animDown.getCurrentFrame();
        }
        if(handler.getKeyManager().attackDown){
            lastAnimation = animDown;
            return animAttackDown.getCurrentFrame();
        }
        if(handler.getKeyManager().left){
            lastAnimation = animLeft;
            return animLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().attackLeft){
            lastAnimation = animLeft;
            return animAttackLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().right){
            lastAnimation = animRight;
            return animRight.getCurrentFrame();
        }
        if(handler.getKeyManager().attackRight){
            lastAnimation = animRight;
            return animAttackRight.getCurrentFrame();
        }
        if(lastAnimation.equals(animRight)){
            return animRight.getFrameAtIndex(0);
        }
        if(lastAnimation.equals(animLeft)){
            return animLeft.getFrameAtIndex(0);
        }
        if(lastAnimation.equals(animDown)){
            return animDown.getFrameAtIndex(0);
        }
        if(lastAnimation.equals(animUp)){
            return animUp.getFrameAtIndex(0);
        }

        return animDown.getFrameAtIndex(0);     // default

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
