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
        this.behavior = new EnemyBehavior(rand.nextInt(3));
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }

    private void setEnemyVelocity(){
        this.enemy.setX_velocity(this.behavior.getX_Velocity());
    }

}
