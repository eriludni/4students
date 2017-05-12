package com.mygdx.game.Model;

import com.mygdx.game.libgdx.Dash;

import java.util.ArrayList;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {

    private Dash game;
    private Player logicalPlayer;
    private ArrayList<Enemy> logicalEnemies;

    public GameWorld(Player player, ArrayList<Enemy> enemies, Dash game) {
        this.logicalPlayer = player;
        this.logicalEnemies = enemies;
        this.game = game;
    }


   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }
   public ArrayList<Enemy> getLogicalEnemies() {
       return logicalEnemies;
   }



}
