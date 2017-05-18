package com.mygdx.game.Model;

import java.util.Random;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private EnemyBehavior behavior;
    Random rand = new Random();

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        this.behavior = new EnemyBehavior(enemy, rand.nextInt(1));
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }

    public float updateX_Velocity() {
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        return behavior.ApplyX_Velocity(currentX_Velocity, currentY_Velocity);
    }
    public float updateY_Velocity(){
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        return behavior.ApplyY_Velocity(currentX_Velocity, currentY_Velocity);
    }

}
