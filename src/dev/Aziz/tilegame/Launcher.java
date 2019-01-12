package dev.Aziz.tilegame;

import dev.Aziz.tilegame.utils.Utils;

public class Launcher {

    public static void main(String[] args){

        //String data = Utils.loadXMLFileAsString("res/map/Map4/map4.xml", 0);
        //System.out.println(data);
        //System.out.println( "-----------------------------------------------------------------------------");
        //System.out.println(Utils.loadXMLFileAsString("res/map/Map4/map4.xml", 1));

        Game game =  new Game("Game", 900, 500);
        game.start();



    }

}
