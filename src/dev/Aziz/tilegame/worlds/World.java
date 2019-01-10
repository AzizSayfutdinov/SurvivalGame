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
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;     //From the world.txt file
    private int[][] tiles;

    //Entities
    private EntityManager entityManager;
    private ItemManager itemManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));

        itemManager = new ItemManager(handler);

        //for(int i = 0;i < 2; i++){
        //    for (int j = 0; j < 2; j++){
        //        entityManager.addEntity(new Tree(handler, 200 + i * 350, 100 + j * 400));
        //    }
        //}
        //entityManager.addEntity(new Rock(handler, 100, 400));
        //entityManager.addEntity(new Tree(handler, 300, 300));
        //entityManager.addEntity(new Enemy(handler,700, 100));
        //entityManager.addEntity(new Enemy(handler,600, 100));
        //entityManager.addEntity(new Enemy(handler,500, 100));
        entityManager.addEntity(new TestEntity(handler, 200, 200));

        Tile.init();
        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        entityManager.tick();
        itemManager.tick();
    }

    public void render(Graphics g){

        //To improve efficiency. We do not need to render all the tiles, but only those which are visible
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH ); // divide by Tiles to get in Tiles not pixels
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        itemManager.render(g);
        entityManager.render(g);
    }

    public Tile getTile(int x,int y){

        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.tiles[445];
        }

        Tile t = Tile.tiles[tiles[x][y] - 1];
        if(t == null){
            return Tile.tiles[0];
        }
        return t;
    }

    private void loadWorld(String path){

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);  //finding the corresponding data
            }
        }

        Tile.createTiles();

    }

    private void loadWorld2(String path){

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);  //finding the corresponding data
            }
        }



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
}
