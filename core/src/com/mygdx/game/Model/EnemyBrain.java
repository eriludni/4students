package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private boolean dead;
    private boolean toBeRemoved;
    private boolean airBorn;
    private int airTime;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        dead = false;
        toBeRemoved = false;
        airBorn = false;
    }

    public void checkDead() {
        if (enemy.isDead()) {
            setToBeRemoved(true);
        }
    }
    public void setAirBorn(boolean airBorn) {
        this.airBorn = airBorn;
    }
    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
