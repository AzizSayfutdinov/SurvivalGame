package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.worlds.Layer;
import dev.Aziz.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;
    private Layer layer1;
    private Layer solidLayer;

    public GameState(Handler handler){
        super(handler);
        //this.world = new World(handler, "res/worlds/world1.txt");
        //this.world = new World(handler, "res/map/Map2/map2.txt");
        this.world = new World(handler, "res/map/Map5/map5.xml");
        handler.setWorld(world);

        layer1 = new Layer(handler);
        layer1.loadLayer("res/map/Map5/map5.xml", 0);

        solidLayer = new Layer(handler);
        solidLayer.loadLayer("res/map/Map5/map5.xml", 1);

    }


    @Override
    public void tick() {

        world.tick();

    }

    @Override
    public void render(Graphics g) {

        world.render(g);
        //layer1.render(g);
        //solidLayer.render(g);



    }
}
