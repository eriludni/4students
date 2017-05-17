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
        this.behavior = new EnemyBehavior(0);
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }

    public void updateX_Velocity() {
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        this.enemy.setX_velocity(behavior.ApplyX_Velocity(currentX_Velocity, currentY_Velocity));
    }
    public void updateY_Velocity(){
        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        this.enemy.setY_velocity(behavior.ApplyY_Velocity(currentX_Velocity, currentY_Velocity));
    }

}
