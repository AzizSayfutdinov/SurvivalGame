package dev.Aziz.tilegame.inventory;

import dev.Aziz.tilegame.Handler;
import dev.Aziz.tilegame.gfx.Assets;
import dev.Aziz.tilegame.gfx.Text;
import dev.Aziz.tilegame.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private int invX = 64;
    private int invY = 48;
    private int invWidth = 512;
    private int invHeight = 384;
    private int invListCenterX = invX + 171;
    private int invListCenterY = invY + invHeight/2 + 5;
    private int invListSpacing = 30;

    private int invImageX = 452, invImageY = 82,
                invImageWidth = 64, invImageHeight = 64;

    private int invCountX = 484, invCountY = 172;

    private int selectedItem = 0;

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<>();

    }

    public void tick(){
       if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
           active = !active;

        if(!active)
            return;

        if(handler.getKeyManager().keyJustPressed((KeyEvent.VK_W)))
            selectedItem--;
        if(handler.getKeyManager().keyJustPressed((KeyEvent.VK_S)))
            selectedItem++;

        if(selectedItem < 0){
            selectedItem = inventoryItems.size() - 1;           //looping around
        } else if(selectedItem >= inventoryItems.size()){
            selectedItem = 0;
        }

    }

    public void render(Graphics g){
        if(!active)
            return;
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len= inventoryItems.size();
        if(len == 0){
            return;
        }
        for(int i = -5; i < 6; i++){
            if(selectedItem + i < 0 || selectedItem + i >= len)     //continue if item out of bounds
                continue;
            if(i == 0)
                Text.drawString(g," > " + inventoryItems.get(selectedItem + i).getName() + " < ", invListCenterX, invListCenterY + i * invListSpacing, true,  Color.YELLOW, Assets.font28);
            else {
                Text.drawString(g,inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true,  Color.WHITE, Assets.font28);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX,invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
    }

    //Inventory Methods

    public void addItem(Item item){
        for(Item i: inventoryItems){
            if(i.getId() == item.getId()){                      //if we have the item already in the inventory
                i.setCount(i.getCount() + item.getCount());     // -> increase the count
                return;
            }
        }
        inventoryItems.add(item);                               // Else the item will be added to the inventory list
    }



    //Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
