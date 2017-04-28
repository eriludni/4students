package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private int health;
    private Vector2 velocity;

    // test for reversing
    private int time = 100;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        this.health = enemy.getHealth();
        this.velocity = enemy.getVelocity();
    }

    public void reduceHealth(int damageValue){
        health -= damageValue;
    }

    public boolean isDead() {
        if (health == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void update(float dt) {
        enemy.b2body.setLinearVelocity(velocity);
        time--;
        if (time == 0) {
            reverseXVelocity();
            time = 100;
        }
    }


    public void reverseXVelocity() {
        velocity.x = -velocity.x;
    }

    public void reverseYVelocity() {
        velocity.y = -velocity.y;
    }
}
