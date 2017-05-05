package com.mygdx.game.Model;

import com.mygdx.game.Model.Character;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Character implements ICharacter{

    public Player(int health, float x_velocity, float y_velocity, float xPos, float yPos, float radius) {
        this.health = health;
        this.x_velocity = x_velocity;
        this.y_velocity = y_velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.dead = false;

        System.out.println("player created");
    }
}