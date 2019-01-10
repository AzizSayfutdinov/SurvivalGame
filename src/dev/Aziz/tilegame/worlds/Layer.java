package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

public class Layer {

    private int[][] layerTilesID;
    private Handler handler;
    private TileManager tileManager;

    public Layer(Handler handler){

        this.handler = handler;
        tileManager = new TileManager(handler);


    }

    public void loadLayer(String path){

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");       //file.split(",") for comma

        layerTilesID = new int[handler.getWorld().getWidth()][handler.getWorld().getHeight()];

        for(int y = 0; y < handler.getWorld().getHeight(); y++){
            for(int x = 0; x < handler.getWorld().getWidth(); x++){
                layerTilesID[x][y] = Utils.parseInt(tokens[(x + y * handler.getWorld().getWidth())]);  //finding the corresponding data
            }
        }

    }

    public int getLayerTilesID(int x, int y) {
        return layerTilesID[x][y];
    }

    public void setLayerTilesID(int[][] layerTilesID) {
        this.layerTilesID = layerTilesID;
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
