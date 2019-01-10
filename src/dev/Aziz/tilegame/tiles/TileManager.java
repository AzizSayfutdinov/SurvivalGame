package dev.Aziz.tilegame.tiles;

import dev.Aziz.tilegame.Handler;

import java.util.ArrayList;

public class TileManager {

    private Handler handler;
    private ArrayList<Tile> tiles;


    public TileManager(Handler handler){

        this.handler = handler;
        tiles = new ArrayList<>();




    }

}
