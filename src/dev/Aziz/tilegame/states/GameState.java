package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(Handler handler){
        super(handler);
        this.world = new World(handler, "res/map/Map5/map5.xml");
        handler.setWorld(world);

    }


    @Override
    public void tick() {

        world.tick();

    }

    @Override
    public void render(Graphics g) {

        world.render(g);



    }
}
