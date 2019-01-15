package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;
import dev.Aziz.tilegame.entities.EntityManager;
import dev.Aziz.tilegame.entities.creatures.Enemy;
import dev.Aziz.tilegame.entities.creatures.Orc;
import dev.Aziz.tilegame.entities.creatures.Player;
import dev.Aziz.tilegame.entities.creatures.Skeleton;
import dev.Aziz.tilegame.entities.statics.House;
import dev.Aziz.tilegame.entities.statics.Tree;
import dev.Aziz.tilegame.items.ItemManager;
import dev.Aziz.tilegame.states.State;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;


public class World{

    public static final int WORLD_WIDTH = 50;
    public static final int WORLD_HEIGHT = 50;
    public static final int SPAWN_X = 656;
    public static final int SPAWN_Y = 1250;

    public static final int ENEMY_SPAWN_X1 = 280;
    public static final int ENEMY_SPAWN_Y1 = 30;

    public static final int ENEMY_SPAWN_X2 = 690;
    public static final int ENEMY_SPAWN_Y2 = 30;

    public static final int ENEMY_SPAWN_X3 = 1300;
    public static final int ENEMY_SPAWN_Y3 = 30;

    protected int spawnX = SPAWN_X, spawnY = SPAWN_Y;
    protected int width = WORLD_WIDTH, height = WORLD_HEIGHT;

    protected int[][] tilesID;
    protected Handler handler;

    public Layer solidLayer;

    private long lastTime;
    private double timer = 0;
    private long currentTime;

    private int enemyWaves = 0;
    private int maxEnemyWaves = 5;

    //Entities
    private EntityManager entityManager;
    private ItemManager itemManager;
    private TileManager tileManager;



    public EntityManager getEntityManager() {
        return entityManager;
    }


    public World(Handler handler, String path){

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 1000, 1000));

        init();

        // DEBUG entities
        //entityManager.addEntity(new Skeleton(handler,ENEMY_SPAWN_X1, ENEMY_SPAWN_Y1));
        //entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X2, ENEMY_SPAWN_Y2));
        //entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X3, ENEMY_SPAWN_Y3));
        //entityManager.addEntity(new Tree(handler, 400, 400, 1));
        //entityManager.addEntity(new Tree(handler, 400, 600, 2));


        loadWorld(path, 0);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);


    }


    public void tick(){

        loadEnemies();
        entityManager.tick();
        //entityManager.getPlayer().postTick();     // used for shooting feature of player
        itemManager.tick();

    }

    public void render(Graphics g){

        tileManager.render(g);
        itemManager.render(g);
        entityManager.render(g);

    }

    public void init(){
        loadForest();
        itemManager = new ItemManager(handler);

        entityManager.addEntity(new House(handler, 223, 1130));
        entityManager.addEntity(new House(handler, 223 + 385, 1130));


    }

    public void loadSolidLayer(){

        solidLayer = new Layer(handler);
        solidLayer.init();
        solidLayer.loadLayer("res/map/Map6/map6.xml", 1);
        tileManager = new TileManager(handler);
        tileManager.solidifyTiles(tileManager.getSolidTiles());

    }

    public void loadForest(){

        int rangeX = (8 - 1) + 1;
        int rangeY = (5 - 1) + 1;


        for(int i = 0; i < 50; i++){
            int x = -80 + ((int)(Math.random() * rangeX) + 1) * (160 + ((int)(Math.random() * 10) - 10));
            int y = ((int)(Math.random() * rangeY) + 1) * (190 + ((int)(Math.random() * 30) - 15));
            int tree = (int)(Math.random() * 2) + 1;

            entityManager.addEntity(new Tree(handler, x, y, tree));
        }

    }

    private void loadEnemies(){

        currentTime = System.currentTimeMillis();
        timer += currentTime - lastTime;
        lastTime = currentTime;
        int waves = 0;

        if(timer > 3000){
            if(enemyWaves > maxEnemyWaves){
                checkIfWon();
                return;
            }


         switch (waves){
             case 0:
                 entityManager.addEntity(new Skeleton(handler, ENEMY_SPAWN_X1, ENEMY_SPAWN_Y1));
                 entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X2, ENEMY_SPAWN_Y2));
                 break;

             case 1:
                 entityManager.addEntity(new Skeleton(handler, ENEMY_SPAWN_X2, ENEMY_SPAWN_Y2));
                 entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X3, ENEMY_SPAWN_Y3));
                 break;

             case 2:
                 entityManager.addEntity(new Skeleton(handler, ENEMY_SPAWN_X3, ENEMY_SPAWN_Y3));
                 entityManager.addEntity(new Orc(handler, ENEMY_SPAWN_X1, ENEMY_SPAWN_Y1));
                 break;

         }

            waves++;

            if(waves > 2)
                waves = 0;

            timer = 0;
            enemyWaves++;
        }
    }

    private void checkIfWon(){

        int count = 0;

        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e instanceof Enemy){
                count++;
            }
        }

        if(count < 1){
            State.setState(handler.getGame().gameWonState);
        }

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

    public void setEnemyWaves(int enemyWaves) {
        this.enemyWaves = enemyWaves;
    }
}
