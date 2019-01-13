package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;

import java.awt.*;

public class OptionState extends State {

    public OptionState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        getInput();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0,0,handler.getGame().getWidth(), handler.getGame().getHeight());
        Text.drawString(g, "NO OPTIONS AVAILABLE YET", 50, 50, false, Color.WHITE, Assets.font28);
    }


    private void getInput(){

        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);
        }

    }

}
