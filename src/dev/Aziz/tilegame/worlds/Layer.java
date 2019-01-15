package dev.Aziz.tilegame.worlds;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.tiles.Tile;
import dev.Aziz.tilegame.tiles.TileManager;
import dev.Aziz.tilegame.utils.Utils;

import java.awt.*;

public class Layer {

    private Handler handler;
    public int[][] solidTilesID;

    private TileManager tileManager;



    public Layer(Handler handler){

        this.handler = handler;
        solidTilesID = new int[handler.getWorld().getWidth()][handler.getWorld().getHeight()];

    }

    public void init(){
        tileManager = new TileManager(handler);
    }

    public void loadLayer(String path, int index){


        String file = Utils.loadXMLFileAsString(path, index);
        String[] tokens = file.split(",");       //file.split(",") for comma

        for(int y = 0; y < handler.getWorld().getWidth(); y++){
            for(int x = 0; x < handler.getWorld().getHeight(); x++){
                solidTilesID[x][y] = Utils.parseInt(tokens[(x + y * handler.getWorld().getWidth())]);  //finding the corresponding data
            }

        }


    }

    public void tick(){

    }

    public void render(Graphics g){

        tileManager.render(g);

    }

    public int[][] getSolidTilesID() {
        return solidTilesID;
    }

    public int getSolidTilesID(int x, int y, int offset) {
        return solidTilesID[x][y] + offset;
    }



    public void setSolidTilesID(int[][] solidTilesID) {
        this.solidTilesID = solidTilesID;
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
