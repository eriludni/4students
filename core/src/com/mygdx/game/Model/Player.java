package com.mygdx.game.Model;

import com.mygdx.game.Model.Character;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Character implements ICharacter{
    private int highscore = 0;

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

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }
}