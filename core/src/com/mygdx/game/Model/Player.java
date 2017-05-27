package com.mygdx.game.Model;

/**
 * Created by Erik on 03/04/2017.
 *
 * @author Erik Lundin
 * Responsibility: Handles the data for the player character in the game
 * Uses: Character
 * Used by: LibgdxPlayer, GameWorld
 */
public class Player extends Character {
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

    /**
     *Checks if the player has moved a certain distance to the right, sets enemies to respawn and sets a new xSpawnPos further ahead
     */
    public void checkxSpawnPosCrossed() {
        if(xPos >= xSpawnPos) {
            setRespawnEnemies(true);
            updatexSpawnPos(xSpawnPos + 20);
        }
    }
}