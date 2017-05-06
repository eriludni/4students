package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private boolean toBeRemoved;
    private int airTime;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        enemy.dead = false;
        toBeRemoved = false;
        enemy.airBorn = false;
    }

    public void checkDead() {
        if (enemy.isDead()) {
            setDead(true);
            setToBeRemoved(true);
        }
    }
    public void setAirBorn(boolean airBorn) {
        enemy.airBorn = airBorn;
    }
    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
    public void setDead(boolean dead) {
        enemy.dead = dead;
    }
}
