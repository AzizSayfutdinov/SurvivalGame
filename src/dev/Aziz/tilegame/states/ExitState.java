package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;

import java.awt.*;

public class ExitState extends State {

    public ExitState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        System.exit(1);
    }

    @Override
    public void render(Graphics g) {

    }
}
