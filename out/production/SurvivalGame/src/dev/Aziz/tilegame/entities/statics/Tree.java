package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.items.Item;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {


    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT  * 2);

        bounds.x = 10;
        bounds.y = (int)(height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x ,(int) y ));
    }
}