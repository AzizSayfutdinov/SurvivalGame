package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.items.Item;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    int treeNr;

    public Tree(Handler handler, float x, float y, int treeNr) {

        super(handler, x, y, 4 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT );

        this.treeNr = treeNr;
        bounds.x = 55;
        bounds.y = 75;
        bounds.width = width - 100;
        bounds.height = height - 90;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(treeNr == 1)
            g.drawImage(Assets.tree1, (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        else
            g.drawImage(Assets.tree2, (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        // bounds
        //g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x ,(int) y ));
    }
}
