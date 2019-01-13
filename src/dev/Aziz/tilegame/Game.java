package dev.Aziz.tilegame;

import dev.Aziz.tilegame.display.Display;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.GameCamera;
import dev.Aziz.tilegame.input.KeyManager;
import dev.Aziz.tilegame.input.MouseManager;
import dev.Aziz.tilegame.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;

    private int width, height;
    public String title;
    private boolean running = false;
    private boolean newGame = false;


    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;
    public State optionState;
    public State creditState;
    public State exitState;
    public State gameOverState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    private  Handler handler;


    public Game(String title, int width, int height){



        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init(){

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);         //additionally add Listener to Canvas to get a smooth operation
        display.getCanvas().addMouseMotionListener(mouseManager);   // Depending on which is currently focus will fire the Event
       //Path: goes by packages, not build folders

        Assets.init();  // for performance sake. Otherwise, we have to load the images every loop

        handler = new Handler(this);

        gameCamera = new GameCamera(handler, 0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        optionState = new OptionState(handler);
        creditState = new CreditState(handler);
        exitState = new ExitState(handler);
        gameOverState = new GameOverState(handler);
        State.setState(menuState);

    }


    private void tick(){    // or update instead

        keyManager.tick();

        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private  void render(){
        bs = display.getCanvas().getBufferStrategy();       //tells the PC how to draw to the screen, it is a hidden screen, where the drawings are drawn on first
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);    //3 are enough
            return;
        }

        g = bs.getDrawGraphics();   //Graphics is like a magic paint brush. The method creates the paint brush
        //clear screen
        g.clearRect(0,0,width, height); //clear Screen before drawing

        if(State.getState() != null){
            State.getState().render(g);
        }

        bs.show();
        g.dispose();

    }

    @Override
    public void run() {

        init();

        int fps = 60;   // frames per second
        double timePerTick = 1000000000 / fps;      // 1s = 1000000000ns; 1/f = T
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        //game loop: picture of game loop
        while(running){

            now = System.nanoTime();
            delta = delta + ((now - lastTime) / timePerTick);       // how many percent of the period time has been used: If bigger than 1 or 100%, we rest delta
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;    /// delta = 0;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();


    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager() { return mouseManager; }

    public GameCamera getGameCamera(){ return gameCamera; }

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

    public synchronized void start(){

        if(running)     // restarting new thread while already running causes a mess, therefore we check it first
            return;
        running = true;
        thread = new Thread(this);
        thread.start(); // calls run-method
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }
}
