package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.creatures.Creature;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.tiles.Tile;

import java.awt.*;

public class TestEntity extends StaticEntity {


    public TestEntity(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.worldTiles[0], (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT, null);
    }

    @Override
    public void die() {

    }
}
