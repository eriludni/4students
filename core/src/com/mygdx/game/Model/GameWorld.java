package com.mygdx.game.Model;

import com.mygdx.game.Dash;

import java.util.ArrayList;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {
    private Player logicalPlayer;
    private int enemyCount = 5;
    private ArrayList<Enemy> logicalEnemies = new ArrayList<Enemy>();
    private ArrayList<EnemyBrain> logicalEnemyBrains = new ArrayList<EnemyBrain>();



    public GameWorld() {
        this.logicalPlayer = new Player(3, 0.1f, 0, 100, 300, 10);
        createLogicalEnemies();
        createLogicalEnemyBrains();
    }

    public void createLogicalEnemies() {
        float xPos = logicalPlayer.getXPos() * Dash.PPM + 200;
        float yPos = logicalPlayer.getYPos() * Dash.PPM + 50;

        for(int i = 0; i < enemyCount; i++) {
            System.out.println(logicalPlayer.getXPos());
            System.out.println(logicalPlayer.getYPos());
            System.out.println(xPos);
            System.out.println(yPos);
            logicalEnemies.add(new Enemy(3, 0.1f, 0, xPos, yPos, 10));
            xPos += 100;
        }
    }
    public void createLogicalEnemyBrains(){
        for(int i  = 0; i < enemyCount; i++){
            logicalEnemyBrains.add(new EnemyBrain(logicalEnemies.get(i)));
        }
    }

    public void removeAllLogicalEnemies() {
        for(int i = 0; i < logicalEnemies.size(); i++) {
            logicalEnemies.remove(i);
        }
        logicalEnemies = new ArrayList<Enemy>();
    }

    public void removeAllLogicalEnemyBrains() {
        for(int i  = 0; i < logicalEnemyBrains.size(); i++){
            logicalEnemyBrains.remove(i);
        }
        logicalEnemyBrains = new ArrayList<EnemyBrain>();
    }

    public void setLogicalEnemies(ArrayList<Enemy> logicalEnemies) {
        this.logicalEnemies = logicalEnemies;
    }

   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }
   public ArrayList<Enemy> getLogicalEnemies() {
       return logicalEnemies;
   }
   public int getEnemyCount() {
       return enemyCount;
   }
}
