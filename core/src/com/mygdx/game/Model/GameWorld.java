package com.mygdx.game.Model;

import com.mygdx.game.libgdx.Dash;

import java.util.ArrayList;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {
    private Player logicalPlayer;
    private ArrayList<Enemy> logicalEnemies = new ArrayList<Enemy>();

    public GameWorld() {
        this.logicalPlayer = new Player(3, 0.1f, 0, 100, 300, 10);
        createLogicalEnemies();
    }

    public void createLogicalEnemies() {
        float xPos = 100;
        float yPos = 300;
        for(int i = 0; i < 10; i++) {
            xPos += 100;
            logicalEnemies.add(new Enemy(3, 0.1f, 0, xPos, yPos, 10));
        }
    }

    public void removeAllLogicalEnemies() {
        for(int i = 0; i < logicalEnemies.size(); i++) {
            logicalEnemies.remove(i);
        }
    }

   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }
   public ArrayList<Enemy> getLogicalEnemies() {
       return logicalEnemies;
   }
}
