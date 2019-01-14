package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.EntityManager;
import dev.Aziz.tilegame.entities.creatures.Enemy;
import dev.Aziz.tilegame.entities.creatures.Orc;
import dev.Aziz.tilegame.entities.creatures.Player;
import dev.Aziz.tilegame.entities.creatures.Skeleton;
import dev.Aziz.tilegame.entities.statics.House;
import dev.Aziz.tilegame.entities.statics.TestEntity;
import dev.Aziz.tilegame.entities.statics.Tree;
import dev.Aziz.tilegame.items.ItemManager;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;


public class World{       //delete extension of world from layer

    public static final int WORLD_WIDTH = 50;
    public static final int WORLD_HEIGHT = 50;
    public static final int SPAWN_X = 100;
    public static final int SPAWN_Y = 100;

    public static final int ENEMY_SPAWN_X1 = 250;
    public static final int ENEMY_SPAWN_Y1 = 30;

    public static final int ENEMY_SPAWN_X2 = 700;
    public static final int ENEMY_SPAWN_Y2 = 30;

    public static final int ENEMY_SPAWN_X3 = 250;
    public static final int ENEMY_SPAWN_Y3 = 30;

    protected int spawnX = SPAWN_X, spawnY = SPAWN_Y;     //From the world.txt file
    protected int width = WORLD_WIDTH, height = WORLD_HEIGHT;

    protected int[][] tilesID;
    protected Handler handler;

    public Layer solidLayer;

    private long lastTime;
    private double timer = 0;
    private long currentTime;

    private int enemies = 0;
    private int maxEnemies = 5;

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

        entityManager.addEntity(new Skeleton(handler,500, 100));
        entityManager.addEntity(new Orc(handler, 500, 300));
        entityManager.addEntity(new TestEntity(handler, 200, 200));
        entityManager.addEntity(new House(handler, 223, 1130));
        entityManager.addEntity(new House(handler, 223 + 385, 1130));
        entityManager.addEntity(new Tree(handler, 400, 400, 1));
        entityManager.addEntity(new Tree(handler, 400, 600, 2));

        loadWorld(path, 0);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);


    }


    public void loadSolidLayer(){

        solidLayer = new Layer(handler);
        solidLayer.init();
        solidLayer.loadLayer("res/map/Map6/map6.xml", 1);
        tileManager = new TileManager(handler);
        tileManager.solidifyTiles(tileManager.getSolidTiles());

    }

    private void loadEnemies(){

        currentTime = System.currentTimeMillis();
        timer += currentTime - lastTime;
        lastTime = currentTime;

        if(timer > 3000){
            if(enemies > maxEnemies)
                return;
            entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X1, ENEMY_SPAWN_Y1));
            entityManager.addEntity(new Skeleton(handler, ENEMY_SPAWN_X2, ENEMY_SPAWN_Y2));
            timer = 0;
            enemies++;
        }


    }

    public void tick(){

        //loadEnemies();
        entityManager.tick();
        itemManager.tick();

    }

    public void render(Graphics g){

        tileManager.render(g);
        itemManager.render(g);
        entityManager.render(g);

    }


    private void loadWorld(String path, int index){

        String file = Utils.loadXMLFileAsString(path, index);
        String[] tokens = file.split(",");       //file.split(",") for comma     //file.split("\\s+")

        tilesID = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tilesID[x][y] = Utils.parseInt(tokens[(x + y * width)]);  //finding the corresponding data, basic math
                System.out.print(tilesID[x][y] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("Loading world finished!");

    }


    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public int getTilesID(int x, int y, int offset) {
        return tilesID[x][y] + offset;
    }

    public void setTilesID(int[][] tilesID) {
        this.tilesID = tilesID;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
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

    public Layer getSolidLayer() {
        return solidLayer;
    }

    public void setSolidLayer(Layer solidLayer) {
        this.solidLayer = solidLayer;
    }
}
