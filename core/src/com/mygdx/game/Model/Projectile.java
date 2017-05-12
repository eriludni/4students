package com.mygdx.game.Model;

import java.awt.color.ICC_ColorSpace;

/**
 * Created by Niklas on 2017-05-08.
 */
public class Projectile {
    private float speed;
    private int damage;

    Projectile(float speed, int damage){
        this.speed = speed;
        this.damage = damage;
    }

    public void dealDamage(IKillable character){
        character.reduceHealth(damage);
    }

    public float getSpeed(){
        return speed;
    }
}
