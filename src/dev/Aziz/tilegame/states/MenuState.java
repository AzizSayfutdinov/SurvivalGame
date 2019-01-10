package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Game;
import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.ui.ClickListener;
import dev.Aziz.tilegame.ui.UIImageButton;
import dev.Aziz.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(220, 220, 128, 32, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {

        uiManager.tick();

        //Going directly to gameState
        //handler.getMouseManager().setUiManager(null);
        //State.setState(handler.getGame().gameState);

    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
