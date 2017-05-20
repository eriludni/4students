package com.mygdx.game.Model;

import com.mygdx.game.Model.Character;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Character implements ICharacter{
    private int highscore = 0;
    private float xSpawnPos = 10;
    private boolean respawnEnemies = false;

    public Player(int health, float x_velocity, float y_velocity, float xPos, float yPos, float radius) {
        this.health = health;
        this.x_velocity = x_velocity;
        this.y_velocity = y_velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.dead = false;

        System.out.println("logical player created");
    }

    /**
     *Setter
     */
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    /**
     *Getter
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     *Getter
     */
    public float getxSpawnPos(){
        return xSpawnPos;
    }

    /**
     *Setter
     */
    public void setxSpawnPos(float spawn) {
        this.xSpawnPos = spawn;
    }

    /**
     *Getter
     */
    public boolean getRespawnEnemies() {
        return respawnEnemies;
    }

    /**
     *Setter
     */
    public void setRespawnEnemies(boolean respawn) {
        this.respawnEnemies = respawn;
    }

    /**
     *Setter
     */
    public void updatexSpawnPos(float xSpawnPos) {
        this.xSpawnPos = xSpawnPos;
    }

    public void checkxSpawnPosCrossed() {
        if(xPos >= xSpawnPos) {
            setRespawnEnemies(true);
            updatexSpawnPos(xSpawnPos + 20);
        }
    }
}