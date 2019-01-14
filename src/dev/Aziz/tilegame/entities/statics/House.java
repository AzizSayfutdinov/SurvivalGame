package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class House extends StaticEntity {

    public House(Handler handler, float x, float y) {
        super(handler, x, y, 5 * Tile.TILEWIDTH, 5 * Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = 60;
        bounds.width = width - 20;
        bounds.height = height - 100;
//

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.house, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        g.setColor(Color.BLUE);
        // bounds
        //g.drawRect((int)(x - handler.getGameCamera().getxOffset()) + bounds.x,(int)(y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }
}
