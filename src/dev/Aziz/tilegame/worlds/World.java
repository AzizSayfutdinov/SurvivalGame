package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.EntityManager;
import dev.Aziz.tilegame.entities.creatures.Enemy;
import dev.Aziz.tilegame.entities.creatures.Player;
import dev.Aziz.tilegame.entities.statics.Rock;
import dev.Aziz.tilegame.entities.statics.TestEntity;
import dev.Aziz.tilegame.entities.statics.Tree;
import dev.Aziz.tilegame.items.ItemManager;
import dev.Aziz.tilegame.tiles.Tile;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;     //From the world.txt file
    private int[][] tilesID;

    //Entities
    private EntityManager entityManager;
    private ItemManager itemManager;
    private TileManager tileManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));

        itemManager = new ItemManager(handler);

        tileManager = new TileManager(handler);

        entityManager.addEntity(new Enemy(handler,500, 100));

        entityManager.addEntity(new TestEntity(handler, 200, 200));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);


    }



    public void tick(){
        entityManager.tick();
        itemManager.tick();
    }

    public void render(Graphics g){


        tileManager.render(g);
        itemManager.render(g);
        entityManager.render(g);
    }


    private void loadWorld(String path){

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");       //file.split(",") for comma
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tilesID = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tilesID[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);  //finding the corresponding data
            }
        }

    }

    public int getTilesID(int x, int y, int offset) {
        return tilesID[x][y] + offset;
    }

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

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }
}
