package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;

public class Layer {

    protected int[][] tilesID;
    protected Handler handler;
    protected TileManager tileManager;

    public Layer(Handler handler){

        this.handler = handler;
        tileManager = new TileManager(handler);


    }

    public void loadLayer(String path, int index){

        String file = Utils.loadXMLFileAsString(path, index);
        String[] tokens = file.split(",");       //file.split(",") for comma

        tilesID = new int[handler.getWorld().getWidth()][handler.getWorld().getHeight()];

        for(int y = 0; y < handler.getWorld().getHeight(); y++){
            for(int x = 0; x < handler.getWorld().getWidth(); x++){
                tilesID[x][y] = Utils.parseInt(tokens[(x + y * handler.getWorld().getWidth())]);  //finding the corresponding data
            }
        }

    }

    public void tick(){

    }

    public void render(Graphics g){
        tileManager.render(g);
    }

    public int getTilesID(int x, int y) {
        return tilesID[x][y];
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
