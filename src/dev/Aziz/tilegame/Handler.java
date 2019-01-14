package dev.Aziz.tilegame;

import dev.Aziz.tilegame.gfx.GameCamera;
import dev.Aziz.tilegame.input.KeyManager;
import dev.Aziz.tilegame.input.MouseManager;
import dev.Aziz.tilegame.worlds.World;

public class Handler {

    private Game game;
    private World world;


    public Game getGame() {
        return game;
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() { return game.getMouseManager(); }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Handler(Game game){
        this.game = game;
    }
}
