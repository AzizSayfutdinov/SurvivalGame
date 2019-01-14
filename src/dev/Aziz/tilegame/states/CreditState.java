package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;

import java.awt.*;

public class CreditState extends State {

    public CreditState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        getInput();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0,0,handler.getGame().getWidth(), handler.getGame().getHeight());
        Text.drawString(g, "CREDITS", handler.getGame().getWidth() / 2, 50, true, Color.YELLOW, Assets.font50);

        Text.drawString(g, "Programmer", handler.getGame().getWidth() / 2, 150, true, Color.GREEN, Assets.font22);
        Text.drawString(g, "Aziz Sayfutdinov", handler.getGame().getWidth() / 2, 200, true, Color.WHITE, Assets.font22);
        Text.drawString(g, "Game Art", handler.getGame().getWidth() / 2, 300, true, Color.GREEN, Assets.font22);
        Text.drawString(g, "Aziz Sayfutdinov", handler.getGame().getWidth() / 2, 350, true, Color.WHITE, Assets.font22);
        Text.drawString(g, "Sound", handler.getGame().getWidth() / 2, 450, true, Color.GREEN, Assets.font22);
        Text.drawString(g, "Aziz Sayfutdinov", handler.getGame().getWidth() / 2, 500, true, Color.WHITE, Assets.font22);
    }


    private void getInput(){

        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);
        }

    }
}
