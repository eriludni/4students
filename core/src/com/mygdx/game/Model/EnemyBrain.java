package com.mygdx.game.Model;

import java.util.Random;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private EnemyBehavior behavior;
    private Random rand = new Random();

    /**
     *Creats an EnemyBrain for an Enemy with a random behavior
     */

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        this.behavior = new EnemyBehavior(rand.nextInt(2));
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }

    /**
     *Sends the current x-velocity to the enemy's behavior and returns the new velocity, affected by the behavior
     */
    public float updateX_Velocity() {
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        return behavior.applyX_Velocity(currentX_Velocity, currentY_Velocity);
    }

    /**
     *Sends the current y-velocity to the enemy's behavior and returns the new velocity, affected by the behavior
     */
    public float updateY_Velocity(){
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        return behavior.applyY_Velocity(currentX_Velocity, currentY_Velocity);
    }

}
