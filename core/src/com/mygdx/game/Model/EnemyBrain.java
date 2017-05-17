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
        this.behavior = new EnemyBehavior(1);
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }

    private void setEnemyVelocity() {
        this.enemy.setX_velocity(this.behavior.getX_Velocity());
    }

    public float[] updateVelocity() {



        float currentX_Velocity = this.enemy.getX_velocity();
        float currentY_Velocity = this.enemy.getY_velocity();

        this.enemy.setX_velocity(this.behavior.UpdateX_Velocity(currentX_Velocity));
        this.enemy.setY_velocity(this.behavior.UpdateY_Velocity(currentY_Velocity));

        float[] updatedV = {this.enemy.getX_velocity(),this.enemy.getY_velocity()};

        return updatedV;    
    }

}
