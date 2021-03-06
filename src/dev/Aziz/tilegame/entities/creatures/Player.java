package dev.Aziz.tilegame.entities.creatures;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.entities.movingObjects.FireBall;
import dev.Aziz.tilegame.entities.movingObjects.MovingObject;
import dev.Aziz.tilegame.gfx.Animation;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.inventory.Inventory;
import dev.Aziz.tilegame.sounds.Sound;
import dev.Aziz.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

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
    private long lastShootTimer, shootCoolDown = 200, shootTimer = shootCoolDown;
    private long lastSoundTimer, soundCoolDown = 300, soundTimer = soundCoolDown;

    //Inventory
    private Inventory inventory;


    public Player(Handler handler, float x, float y){
        super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 20;
        bounds.y = 25;
        bounds.width = 23;
        bounds.height = 30;


        health = 100;

        lastAttackTimer = System.currentTimeMillis();
        lastShootTimer = System.currentTimeMillis();
        lastSoundTimer = System.currentTimeMillis();

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
        getInput();
        move();


        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();

        checkDirection();
        playSounds();
        shoot();


    }

    private void playSounds(){

        soundTimer += System.currentTimeMillis() - lastSoundTimer;
        lastSoundTimer = System.currentTimeMillis();

        if(soundTimer < soundCoolDown)
            return;


        if(handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left || handler.getKeyManager().right){

            Sound sound = new Sound(Assets.walkingSound);
            sound.setVolume(-15);
            new Thread(sound).start();

        }

        soundTimer = 0;

    }

    private void checkDirection(){

        // remember direction player was looking at last

    }


    private void shoot(){


        shootTimer += System.currentTimeMillis() - lastShootTimer;
        lastShootTimer = System.currentTimeMillis();

        if(shootTimer < shootCoolDown)
            return;

        if(handler.getKeyManager().shoot){
            handler.getWorld().getMovingObjectsManager().addObject(new FireBall(handler));    //concurrentModificationException, therefore postTick()
            Sound sound = new Sound(Assets.fireBallSound);
            sound.setVolume(-22);
            new Thread(sound).start();
        }

        shootTimer = 0;


    }


    private void checkAttacks(){

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        if(inventory.isActive()){
            return;
        }

        // if(handler.getKeyManager().attackRight || handler.getKeyManager().attackLeft || handler.getKeyManager().attackUp || handler.getKeyManager().attackDown)
        //     new Thread(new Sound(Assets.attackingSwordSound)).start();
        if(handler.getKeyManager().attack){
            new Thread((new Sound(Assets.attackingSwordSound))).start();
        }


        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 25; //px
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().attack && lastAnimation == animUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;                       // -arSize because we attack upwards
        } else if(handler.getKeyManager().attack && lastAnimation == animDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if(handler.getKeyManager().attack && lastAnimation == animLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if(handler.getKeyManager().attack && lastAnimation == animRight){
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
        // g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
        // g.setColor(Color.RED);
        // g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + fireBallBounds.x,(int)(y - handler.getGameCamera().getyOffset()) + fireBallBounds.y, fireBallBounds.width, fireBallBounds.height);


        // Health
        if(health > 70)
            g.setColor(Color.GREEN);
        else if(health > 20)
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.RED);
        g.fillRect(handler.getGame().getWidth() - 210, 20,  health * 2, 35);
        g.setColor(Color.BLACK);
        g.drawRect(handler.getGame().getWidth() - 210, 20, 200, 35);


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
        if(handler.getKeyManager().attack && lastAnimation == animUp) {
            lastAnimation = animUp;
            return animAttackUp.getCurrentFrame();
        }
        if(handler.getKeyManager().down){
            lastAnimation = animDown;
            return animDown.getCurrentFrame();
        }
        if(handler.getKeyManager().attack && lastAnimation == animDown){
            lastAnimation = animDown;
            return animAttackDown.getCurrentFrame();
        }
        if(handler.getKeyManager().left){
            lastAnimation = animLeft;
            return animLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().attack && lastAnimation == animLeft){
            lastAnimation = animLeft;
            return animAttackLeft.getCurrentFrame();
        }
        if(handler.getKeyManager().right){
            lastAnimation = animRight;
            return animRight.getCurrentFrame();
        }
        if(handler.getKeyManager().attack && lastAnimation == animRight){
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

    public Animation getLastAnimation() {
        return lastAnimation;
    }

    public Animation getAnimDown() {
        return animDown;
    }

    public Animation getAnimUp() {
        return animUp;
    }

    public Animation getAnimRight() {
        return animRight;
    }

    public Animation getAnimLeft() {
        return animLeft;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
