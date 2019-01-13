package dev.Aziz.tilegame.tiles;

import dev.Aziz.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

    //STATIC STUFF: Used as Tile Manager

    // Es macht keinen Sinn die Tiles static zu machen, weil ich sie dynamisch ändern will.
    // Manchmal sollte man durch ein Tile durchgehen können und manchmal nicht.



    //CLASS

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected boolean solid = false;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

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
