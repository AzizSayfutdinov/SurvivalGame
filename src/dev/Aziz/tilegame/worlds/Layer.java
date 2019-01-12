package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;

public class Layer {

    public static final int WORLD_WIDTH = 50;
    public static final int WORLD_HEIGHT = 50;

    protected int[][] tilesID;
    protected Handler handler;
    private TileManager tileManager;

    protected int width = WORLD_WIDTH, height = WORLD_HEIGHT;


    public Layer(Handler handler){

        this.handler = handler;
        tileManager = new TileManager(handler);


    }

    public void loadLayer(String path, int index){

        String file = Utils.loadXMLFileAsString(path, index);
        String[] tokens = file.split(",");       //file.split(",") for comma

        tilesID = new int[width][height];
        System.out.println("Layer-IDs: \n ------------------------------------------------------\n");
        for(int y = 0; y < width; y++){
            for(int x = 0; x < height; x++){
                tilesID[x][y] = Utils.parseInt(tokens[(x + y * width)]);  //finding the corresponding data
                System.out.print(tilesID[x][y] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("Loading Layer finished! \n-----------------------------------------------------------------");

    }

    public void tick(){

    }

    public void render(Graphics g){
        tileManager.render(g);
    }

    public int getTilesID(int x, int y, int offset) {
        return tilesID[x][y] + offset;
    }

    public void setTilesID(int[][] tilesID) {
        this.tilesID = tilesID;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }
}
