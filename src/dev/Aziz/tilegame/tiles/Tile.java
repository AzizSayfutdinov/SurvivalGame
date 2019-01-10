package dev.Aziz.tilegame.tiles;

import dev.Aziz.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

    //STATIC STUFF: Used as Tile Manager

    public static Tile[] tiles = new Tile[1440];


    public static void createTiles(){

        for(int i = 0; i < tiles.length; i++) {

            tiles[i] = new WorldTile(Assets.worldTiles[i], i, false);
        }
    }

    public static void init(){

        createTiles();

    }


    //CLASS

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected boolean solid = true;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x,y,TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public int getId(){
        return id;
    }

}
