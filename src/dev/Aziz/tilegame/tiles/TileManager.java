package dev.Aziz.tilegame.tiles;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class TileManager {

    public static final int WORLD_TILES = 1440;

    private Handler handler;
    private ArrayList<Tile> tiles;
    private ArrayList<Integer> solidTiles;


    public TileManager(Handler handler){

        this.handler = handler;
        tiles = new ArrayList<>();
        solidTiles = new ArrayList<>();

        initTiles();

    }

    public void initTiles(){

        for(int i = 0; i < WORLD_TILES; i++){
            addTile(new Tile(Assets.worldTiles[i], i));
        }

        solidify(solidTiles);

    }

    private void solidify(ArrayList<Integer> list){

        createSolidTiles();

        for(int i = 0; i < TileManager.WORLD_TILES; i++) {
            for (Integer id : list) {
                if (tiles.get(i).getId() == id){
                    tiles.get(i).setSolid(true);
                }
            }
        }

    }



    private void createSolidTiles(){

        addSolidTile(486);
        addSolidTile(405);
        addSolidTile(407);
        addSolidTile(365);
        addSolidTile(366);
        addSolidTile(367);

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

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

    }


    public Tile getTile(int x,int y){

        if(x < 0 || y < 0 || x >= handler.getWorld().getWidth() || y >= handler.getWorld().getHeight()){
            return tiles.get(1);      //default return value
        }

        Tile t = tiles.get(handler.getWorld().getTilesID(x, y, -1));

        if(t == null){
            return tiles.get(1);
        }
        return t;
    }

    public void addTile(Tile t){
        tiles.add(t);
    }

}
