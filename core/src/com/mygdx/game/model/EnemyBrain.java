package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private int health;
    private Vector2 velocity;
    private boolean dead;
    private boolean toBeRemoved;

    // test for reversing
    private int time = 100;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        this.health = enemy.getHealth();
        this.velocity = enemy.getVelocity();
        dead = false;
        toBeRemoved = false;
    }

    public void reduceHealth(int damageValue){
        health -= damageValue;
        enemy.setHealth(health);
    }

    public boolean isDead() {
        if (health == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void checkDead() {
        if (isDead()) {
            toBeRemoved = true;
        }
    }

    public void removeEnemy() {
        if(toBeRemoved && !dead) {
            enemy.getWorld().destroyBody(enemy.b2body);
            dead = true;
        }
    }

    public void update(float dt) {
        enemy.b2body.setLinearVelocity(velocity);

        // For testing purposes only, to be removed later
        time--;
        reduceHealth(1);
        if (time == 0) {
            reverseXVelocity();
            time = 100;
        }
        checkDead();
        removeEnemy();
    }


    public void reverseXVelocity() {
        velocity.x = -velocity.x;
    }

    public void reverseYVelocity() {
        velocity.y = -velocity.y;
    }
}
