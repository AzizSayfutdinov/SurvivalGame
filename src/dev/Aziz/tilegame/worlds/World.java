package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.EntityManager;
import dev.Aziz.tilegame.entities.creatures.Enemy;
import dev.Aziz.tilegame.entities.creatures.Player;
import dev.Aziz.tilegame.entities.statics.TestEntity;
import dev.Aziz.tilegame.items.ItemManager;


import java.awt.*;

public class World extends Layer{       //delete extension of world from layer


    public static final int SPAWN_X = 100;
    public static final int SPAWN_Y = 100;

    protected int spawnX = SPAWN_X, spawnY = SPAWN_Y;     //From the world.txt file


    //Entities
    private EntityManager entityManager;
    private ItemManager itemManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public World(Handler handler, String path){
        super(handler);

        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        itemManager = new ItemManager(handler);

        entityManager.addEntity(new Enemy(handler,500, 100));
        entityManager.addEntity(new TestEntity(handler, 200, 200));

        loadWorld(path, 0);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);


    }



    public void tick(){
        entityManager.tick();
        itemManager.tick();
    }

    public void render(Graphics g){

        super.render(g);
        itemManager.render(g);
        entityManager.render(g);

    }

    private void loadWorld(String path, int index){
        System.out.println("World: ");
        loadLayer(path, index);

    }


    //private void loadWorld(String path, int index){

    //    String file = Utils.loadXMLFileAsString(path, index);
    //    String[] tokens = file.split(",");       //file.split(",") for comma     //file.split("\\s+")

    //    tilesID = new int[width][height];

    //    for(int y = 0; y < height; y++){
    //        for(int x = 0; x < width; x++){
    //            tilesID[x][y] = Utils.parseInt(tokens[(x + y * width)]);  //finding the corresponding data, basic math
    //            System.out.print(tilesID[x][y] + " ");
    //        }
    //        System.out.println("\n");
    //    }
    //    System.out.println("Loading world finished!");

    //}


    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

}
