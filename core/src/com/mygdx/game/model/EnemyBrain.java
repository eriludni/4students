package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

import static java.lang.System.out;

/**
 * Created by Lucas on 2017-04-28.
 */
public class EnemyBrain {
    private Enemy enemy;
    private int health;
    private Vector2 velocity;
    private boolean dead;
    private boolean toBeRemoved;
    private boolean airBorn;
    private int airTime;

    // test for reversing
    private int time = 100;

    public EnemyBrain(Enemy enemy) {
        this.enemy = enemy;
        this.health = enemy.getHealth();
        this.velocity = enemy.getVelocity();
        dead = false;
        toBeRemoved = false;
        airBorn = false;
        airTime = 50;
    }

    public void jump() {
        enemy.b2body.applyLinearImpulse(new Vector2(0, 20), enemy.b2body.getWorldCenter(), true);
        airBorn = true;
    }

    public void checkDead() {
        if (enemy.isDead()) {
            toBeRemoved = true;
        }
    }

    public void removeEnemy() {
        if(toBeRemoved && !dead) {
            enemy.getWorld().destroyBody(enemy.b2body);
            dead = true;
        }
    }

    public void checkAirBorn(int AT) {
        if(AT > 0) {
            airBorn = true;
        }
        else {
            airBorn = false;
            airTime = 50;
        }
    }

    public void update(float dt) {
        enemy.b2body.setLinearVelocity(velocity);

        // For testing
        time--;
        //enemy.reduceHealth(1);
        if (time == 0) {
            enemy.reverseXVelocity();
            time = 100;
        }

        if(enemy.b2body.getLinearVelocity().y == 0 && airBorn == false)
        {
            jump();
        }
        else {
            airTime--;
            checkAirBorn(airTime);
        }

        checkDead();
        removeEnemy();
    }
}
