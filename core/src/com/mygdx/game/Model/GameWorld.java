package com.mygdx.game.Model;

import com.mygdx.game.Utils.CONSTANTS;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {
    private Player logicalPlayer;
    private Random rand = new Random();
    private int enemyCount = 5;
    private int powerUpCount = 1;
    private ArrayList<Enemy> logicalEnemies = new ArrayList<Enemy>();
    private ArrayList<EnemyBrain> logicalEnemyBrains = new ArrayList<EnemyBrain>();
    private ArrayList<PowerUp> logicalPowerUps = new ArrayList<PowerUp>();



    public GameWorld() {
        this.logicalPlayer = new Player(6, 0.1f, 0, 700, 300, 14);
        createLogicalEnemies();
        createLogicalEnemyBrains();
        createLogicalPowerUps();
    }

    /**
     *Creates logical enemies at positions relative to the player
     */
    public void createLogicalEnemies() {
        float xPos = logicalPlayer.getXPos() * CONSTANTS.PPM + 500;
        float yPos = logicalPlayer.getYPos() * CONSTANTS.PPM + 50;

        for(int i = 0; i < enemyCount; i++) {
            logicalEnemies.add(new Enemy(3, 0.1f, 0, xPos, yPos, 14));
            xPos += 200;
        }
    }

    /**
     *Creates logical enemy brains for each logical enemy
     */
    public void createLogicalEnemyBrains(){
        for(int i  = 0; i < enemyCount; i++){
            logicalEnemyBrains.add(new EnemyBrain(logicalEnemies.get(i)));
        }
    }

    /**
     *Creates logical powerups at positions relative to the player
     */
    public void createLogicalPowerUps() {
        int x = 200;
        int y = 50;
        float offsetX = logicalPlayer.getXPos() * CONSTANTS.PPM + x;
        float offsetY = logicalPlayer.getYPos() * CONSTANTS.PPM + y;
        for(int i = 0; i < powerUpCount; i++) {
            x = (rand.nextInt(5) + 1) * 100;
            y = rand.nextInt(26);
            logicalPowerUps.add(new PowerUp(offsetX, offsetY));
            offsetX += x;
            offsetY = logicalPlayer.getYPos() * CONSTANTS.PPM + y;
        }
    }

    /**
     *Removes all logical enemies and creates a new array for logical enemies
     */
    public void removeAllLogicalEnemies() {
        for(int i = 0; i < logicalEnemies.size(); i++) {
            logicalEnemies.remove(i);
        }
        logicalEnemies = new ArrayList<Enemy>();
    }

    /**
     *Removes all logical enemy brains and creates a new array for logical enemy brains
     */
    public void removeAllLogicalEnemyBrains() {
        for(int i  = 0; i < logicalEnemyBrains.size(); i++){
            logicalEnemyBrains.remove(i);
        }
        logicalEnemyBrains = new ArrayList<EnemyBrain>();
    }

    /**
     *Removes all logical powerups and creates a new array for logical powerups
     */
    public void removeAllLogicalPowerUps() {
        for(int i = 0; i < logicalPowerUps.size(); i++) {
            logicalPowerUps.remove(i);
        }
        logicalPowerUps = new ArrayList<PowerUp>();
    }

    /**
     *Getter
     */
   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }

    /**
     *Getter
     */
   public ArrayList<Enemy> getLogicalEnemies() {
       return logicalEnemies;
   }

    /**
     *Getter
     */
   public ArrayList<PowerUp> getLogicalPowerUps() {
       return logicalPowerUps;
   }

    /**
     *Getter
     */
   public ArrayList<EnemyBrain> getLogicalEnemyBrains() {
       return logicalEnemyBrains;
   }

    /**
     *Getter
     */
   public int getEnemyCount() {
       return enemyCount;
   }

    /**
     *Getter
     */
   public int getPowerUpCount() {
       return powerUpCount;
   }
}
