package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;
import dev.Aziz.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    private long previousTime;
    private long currentTime = System.currentTimeMillis();
    private double elapsedTime = 0;

    public GameState(Handler handler){
        super(handler);
        this.world = new World(handler, "res/map/Map6/map6.xml");
        handler.setWorld(world);
        world.loadSolidLayer();

        previousTime = System.currentTimeMillis();

    }


    @Override
    public void tick() {

        world.tick();
        currentTime = System.currentTimeMillis();
        elapsedTime += (currentTime - previousTime) / 1000.0;
        previousTime = currentTime;
        getInput();


    }

    @Override
    public void render(Graphics g) {

        world.render(g);

        Text.drawString(g, String.format("%02d:%02d", (int) (elapsedTime / 60), (int)(elapsedTime % 60 )), 30, 30, false, Color.WHITE, Assets.font28);
        Text.drawString(g, "POINTS: " + handler.getWorld().getEntityManager().getPlayer().getPoints(), handler.getGame().getWidth() - 212, 90, false, Color.WHITE, Assets.font28);

    }

    private void getInput(){

        if(handler.getKeyManager().esc){
            handler.getGame().setNewGame(true);
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);

        }

    }
}
