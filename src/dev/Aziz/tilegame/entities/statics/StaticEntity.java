package dev.Aziz.tilegame.entities.statics;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {


    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


}
