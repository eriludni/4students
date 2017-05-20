package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-27.
 */
public class Enemy extends Character implements ICharacter{

    EnemyBrain brain;

    public Enemy(int health, float x_velocity, float y_velocity, float xPos, float yPos, float radius){
        this.brain = new EnemyBrain(this);

        this.health = health;
        this.x_velocity = x_velocity;
        this.y_velocity = y_velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.dead = false;

    }

    /**
     *Getter
     */
    public EnemyBrain getBrain(){
        return this.brain;
    }
}
