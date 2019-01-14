package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.items.Item;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {



    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH,Tile.TILEHEIGHT);

        //set bounds for the collision detection
        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.rock, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH,Tile.TILEHEIGHT, null);
    }

    @Override
    public void die() {
        //handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x ,(int) y));
    }
}
