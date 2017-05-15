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

    private void setEnemyVelocity() {
        this.enemy.setX_velocity(this.behavior.getX_Velocity());
    }

    public float updateVelocity() {
        float currentX_Velocity = this.enemy.getX_velocity();
        this.enemy.setX_velocity(this.behavior.UpdateX_Velocity(currentX_Velocity));
        return this.enemy.getX_velocity();
    }

}
