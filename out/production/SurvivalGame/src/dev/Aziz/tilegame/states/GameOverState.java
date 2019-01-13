package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;

import java.awt.*;

public class GameOverState extends State {

    private long timer = 0;
    private long lastTime = 1000;
    private long waitTime = 3000;   // 3s

    private double survivedTime;

    public GameOverState(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        getInput();

    }

    @Override
    public void render(Graphics g) {
        Text.drawString(g, "GAME OVER!", 300, 300, false, Color.RED, Assets.font28);
        Text.drawString(g, "Press ESC to get to the MENU", 100, 150, false, Color.BLACK, Assets.font28);

        Text.drawString(g, "SCORE: " + handler.getWorld().getEntityManager().getPlayer().getPoints() + " Points", 100, 400, false, Color.BLACK,Assets.font28);
    }

    private void getInput(){

        if(handler.getKeyManager().esc){
            handler.getGame().setNewGame(true);
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);

        }

    }

}
