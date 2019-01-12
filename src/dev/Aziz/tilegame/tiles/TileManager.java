package dev.Aziz.tilegame.tiles;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.worlds.Layer;

import java.awt.*;
import java.util.ArrayList;

public class TileManager {

    public static final int WORLD_TILES = 1440;

    private Handler handler;
    private ArrayList<Tile> tiles;

    private ArrayList<Integer> solidTiles;
    private Layer solidLayer;
    private int[][] solidTilesIDs;


    public TileManager(Handler handler){

        this.handler = handler;
        this.solidLayer = handler.getWorld().getSolidLayer();
        tiles = new ArrayList<>();
        solidTiles = new ArrayList<>();
        solidTilesIDs = solidLayer.getSolidTilesID();

        initTiles();

    }

    public void initTiles(){

        for(int i = 0; i < WORLD_TILES; i++){
            addTile(new Tile(Assets.worldTiles[i], i));
        }

    }

    public void solidifyTiles(ArrayList<Integer> list){

        createSolidTiles();


        for (Integer id : list) {
            for(int i = 0; i < TileManager.WORLD_TILES; i++) {
                if (tiles.get(i).getId() == id) {
                    tiles.get(i).setSolid(true);
                }
            }
        }

    }


    private void createSolidTiles(){

        for(int i = 0; i < solidTilesIDs.length; i++){
            for(int j = 0; j < solidTilesIDs[i].length; j++){
                if(solidLayer.getSolidTilesID(i, j, -1) < 1){
                    continue;
                } else {
                    addSolidTile(solidLayer.getSolidTilesID(i, j,-1));
                }
            }
        }

        //addSolidTile(486);
        //addSolidTile(405);
        //addSolidTile(407);
        //addSolidTile(365);
        //addSolidTile(366);
        //addSolidTile(367);

    }

    private void addSolidTile(int mapIndex){
        solidTiles.add(mapIndex - 1);
    }

    public void tick(){

    }

    public void render(Graphics g){

        //To improve efficiency. We do not need to render all the tiles, but only those which are visible
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH ); // divide by Tiles to get in Tiles not pixels
        int xEnd = (int) Math.min(handler.getWorld().getWidth(), (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(handler.getWorld().getHeight(), (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        // Render World Layer
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                if(getTile(x,y).getId() == 1403){
                    System.out.println("0 TILE!!!!!");
                }
                else {
                    getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                    getSolidTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                }
            }
        }

    }


    public Tile getTile(int x,int y){

        if(x < 0 || y < 0 || x >= handler.getWorld().getWidth() || y >= handler.getWorld().getHeight()){
            return tiles.get(1403);      //default return value
        }

        Tile t;

        if(handler.getWorld().getTilesID(x,y,-1) < 0) {
            t = tiles.get(1403);
        } else {
            t = tiles.get(handler.getWorld().getTilesID(x, y, -1));
        }

        if(t == null){
            return tiles.get(1403);
        }
        return t;
    }

    public Tile getSolidTile(int x,int y){

        if(x < 0 || y < 0 || x >= handler.getWorld().getWidth() || y >= handler.getWorld().getHeight()){
            return tiles.get(1403);      //default return value
        }

        Tile t;

        if(handler.getWorld().getSolidLayer().getSolidTilesID(x,y,-1) < 0) {
            t = tiles.get(1403);
        } else {
            t = tiles.get(handler.getWorld().getSolidLayer().getSolidTilesID(x, y, -1));
        }

        if(t == null){
            return tiles.get(1403);
        }
        return t;
    }



    public void addTile(Tile t){
        tiles.add(t);
    }

    public ArrayList<Integer> getSolidTiles() {
        return solidTiles;
    }

    public void setSolidTiles(ArrayList<Integer> solidTiles) {
        this.solidTiles = solidTiles;
    }


}
