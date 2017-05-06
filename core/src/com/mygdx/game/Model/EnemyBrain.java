package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        enemy.dead = false;
        enemy.toBeRemoved = false;
        enemy.airBorn = false;
    }
}
