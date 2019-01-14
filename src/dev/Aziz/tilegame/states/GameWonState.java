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

public class GameWonState extends State {

    private UIManager uiManager;

    private int button_width = 64;
    private int button_height = 64;


    public GameWonState(Handler handler) {
        super(handler);
        //this.uiManager = new UIManager(handler);
        //handler.getMouseManager().setUiManager(uiManager);
//
        //uiManager = new UIManager(handler);
        //handler.getMouseManager().setUiManager(uiManager);
//
        //uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - button_width / 2, handler.getGame().getHeight() / 2 - button_height / 2 + 100, button_width, button_height, Assets.btn_restart, new ClickListener() {
        //    @Override
        //    public void onClick() {
        //        handler.getMouseManager().setStateActive(false);
        //        if(handler.getGame().isNewGame()){
        //            recreateWorld();
        //        }
        //        State.setState(handler.getGame().gameState);
        //    }
        //}));
    }

    @Override
    public void tick() {
        //uiManager.tick();
        getInput();

    }

    @Override
    public void render(Graphics g) {
        Text.drawString(g, "YOU WIN!", handler.getGame().getWidth() / 2, handler.getGame().getHeight() / 2, true, Color.RED, Assets.font100);
        Text.drawString(g, "Press ESC to get to the MENU", 100, handler.getGame().getHeight() - 100, false, Color.BLACK, Assets.font28);
        Text.drawString(g, "SCORE: " + handler.getWorld().getEntityManager().getPlayer().getPoints() + " Points", 100, 150, false, Color.BLACK,Assets.font28);

        //uiManager.render(g);

    }

    private void getInput(){

        if(handler.getKeyManager().esc){
            handler.getGame().setNewGame(true);
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setStateActive(true);

        }

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
