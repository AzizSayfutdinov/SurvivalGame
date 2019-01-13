package dev.Aziz.tilegame.tiles;

import java.awt.image.BufferedImage;

public class WorldTile extends Tile {

    public WorldTile(BufferedImage texture, int id, boolean solid) {
        super(texture, id);
        this.solid = solid;

    }
}
