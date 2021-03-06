package dev.Aziz.tilegame.entities;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {        // we sort all the entities so that the entities on the top are rendered first
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return  -1;
            else
                return 1;
        }
    };

    public EntityManager(Handler handler, Player player){

        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);

    }

    public void tick(){
        Iterator<Entity> it = entities.iterator();      // to avoid concurrentModificationException: occurs when you try to modify a list when it is being iterated

        while(it.hasNext()){
            Entity e = it.next();
            e.tick();

            if(!e.isActive()){
                it.remove();
            }

        }
        entities.sort(renderSorter);

    }

    public void render(Graphics g){

        for(Entity e: entities){
            e.render(g);
        }

        player.postRender(g);

    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }


}
