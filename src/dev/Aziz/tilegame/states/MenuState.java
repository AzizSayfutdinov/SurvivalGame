package dev.Aziz.tilegame.states;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.entities.creatures.Player;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;
import dev.Aziz.tilegame.ui.ClickListener;
import dev.Aziz.tilegame.ui.UIImageButton;
import dev.Aziz.tilegame.ui.UIManager;
import dev.Aziz.tilegame.worlds.World;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    private int button_width = 256;
    private int button_height = 64;

    public MenuState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - button_width / 2, handler.getGame().getHeight() / 2 - button_height / 2, button_width, button_height, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setStateActive(false);
                if(handler.getGame().isNewGame()){
                    recreateWorld();
                }
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - button_width / 2, handler.getGame().getHeight() / 2 - button_height / 2 + button_height, button_width, button_height, Assets.btn_options, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setStateActive(false);
                State.setState(handler.getGame().optionState);
            }
        }));

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - button_width / 2, handler.getGame().getHeight() / 2 - button_height / 2 + button_height * 2, button_width, button_height, Assets.btn_credits, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setStateActive(false);
                State.setState(handler.getGame().creditState);
            }
        }));

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - button_width / 2, handler.getGame().getHeight() / 2 - button_height / 2 + button_height * 3, button_width, button_height, Assets.btn_exit, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setStateActive(false);
                State.setState(handler.getGame().exitState);
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
        g.fillRect(0,0,handler.getGame().getWidth(), handler.getGame().getHeight());
        Text.drawString(g, "WELCOME TO THE SURVIVAL-GAME!", 50, 50, false, Color.WHITE, Assets.font50);
        Text.drawString(g, "Use the W,A,S,D keys to move!", 50, 100, false, Color.RED, Assets.font28);
        Text.drawString(g, "Use arrow keys to attack!", 50, 130, false, Color.RED, Assets.font28);
        Text.drawString(g, "Good luck!", 50, 160, false, Color.RED, Assets.font28);

        uiManager.render(g);

    }


    public UIManager getUiManager() {
        return uiManager;
    }

    private void recreateWorld(){

        Player p = new Player(handler, World.SPAWN_X, World.SPAWN_Y);
        handler.getWorld().getEntityManager().setPlayer(p);
        handler.getWorld().getEntityManager().getEntities().removeAll(handler.getWorld().getEntityManager().getEntities());
        handler.getWorld().setEnemyWaves(0);
        handler.getWorld().getEntityManager().addEntity(p);
        handler.getWorld().init();

    }

}
