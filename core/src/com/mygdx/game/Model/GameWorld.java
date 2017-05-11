package com.mygdx.game.Model;

import com.mygdx.game.libgdx.Dash;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {

    private Dash game;
    private Player logicalPlayer;
    private Enemy logicalEnemy;

    public GameWorld(Player player, Enemy enemy, Dash game) {
        this.logicalPlayer = player;
        this.logicalEnemy = enemy;
        this.game = game;
    }


   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }
   public Enemy getLogicalEnemyCharacter() {
       return logicalEnemy;
   }



}
