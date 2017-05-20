package com.mygdx.game.Model;

import com.mygdx.game.Dash;
import com.mygdx.game.Utils.CONSTANTS;

import java.util.ArrayList;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {
    private Player logicalPlayer;
    private int enemyCount = 5;
    private int powerUpCount = 2;
    private ArrayList<Enemy> logicalEnemies = new ArrayList<Enemy>();
    private ArrayList<EnemyBrain> logicalEnemyBrains = new ArrayList<EnemyBrain>();
    private ArrayList<PowerUp> logicalPowerUps = new ArrayList<PowerUp>();



    public GameWorld() {
        this.logicalPlayer = new Player(3, 0.1f, 0, 100, 300, 20);
        createLogicalEnemies();
        createLogicalEnemyBrains();
        createLogicalPowerUps();
    }

    public void createLogicalEnemies() {
        float xPos = logicalPlayer.getXPos() * CONSTANTS.PPM + 500;
        float yPos = logicalPlayer.getYPos() * CONSTANTS.PPM + 50;

        for(int i = 0; i < enemyCount; i++) {
            System.out.println(logicalPlayer.getXPos());
            System.out.println(logicalPlayer.getYPos());
            System.out.println(xPos);
            System.out.println(yPos);
            logicalEnemies.add(new Enemy(3, 0.1f, 0, xPos, yPos, 20));
            xPos += 200;
        }
    }
    public void createLogicalEnemyBrains(){
        for(int i  = 0; i < enemyCount; i++){
            logicalEnemyBrains.add(new EnemyBrain(logicalEnemies.get(i)));
        }
    }

    public void createLogicalPowerUps() {
        float offsetX = logicalPlayer.getXPos() * CONSTANTS.PPM + 200;
        float offsetY = logicalPlayer.getYPos() * CONSTANTS.PPM + 50;
        for(int i = 0; i < powerUpCount; i++) {
            logicalPowerUps.add(new PowerUp(offsetX, offsetY));
            offsetX += 200;
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

    public void removeAllLogicalPowerUps() {
        for(int i = 0; i < logicalPowerUps.size(); i++) {
            logicalPowerUps.remove(i);
        }
        logicalPowerUps = new ArrayList<PowerUp>();
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
   public ArrayList<PowerUp> getLogicalPowerUps() {
       return logicalPowerUps;
   }
   public int getEnemyCount() {
       return enemyCount;
   }
   public int getPowerUpCount() {
       return powerUpCount;
   }
}
