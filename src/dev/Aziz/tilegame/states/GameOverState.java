package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;

import java.awt.*;

public class GameOverState extends State {

    public GameOverState(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

        getInput();

    }

    @Override
    public void render(Graphics g) {
        Text.drawString(g, "GAME OVER!", handler.getGame().getWidth() / 2, handler.getGame().getHeight() / 2, true, Color.RED, Assets.font100);
        Text.drawString(g, "Press ESC to get to the MENU", 100, handler.getGame().getHeight() - 100, false, Color.BLACK, Assets.font28);

        Text.drawString(g, "SCORE: " + handler.getWorld().getEntityManager().getPlayer().getPoints() + " Points", 100, 150, false, Color.BLACK,Assets.font28);
    }

    private void getInput(){

        if(handler.getKeyManager().esc){
            handler.getGame().setNewGame(true);
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);

        }

    }

}
