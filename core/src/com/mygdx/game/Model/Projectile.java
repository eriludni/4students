package com.mygdx.game.Model;

import java.awt.*;
import java.awt.color.ICC_ColorSpace;

import static java.lang.Math.*;

/**
 * Created by Niklas on 2017-05-08.
 */
public class Projectile {
    private float speed;
    private int damage;
    private boolean hasCollided;
    private double angle;

    public Projectile(float speed, int damage){
        this.speed = speed;
        this.damage = damage;
        hasCollided = false;
    }

    public Point getLaunchPosition(Point characterPosition, Point targetPosition, float hitBoxRadius)
    {
        double yDelta = targetPosition.y - characterPosition.y;
        double xDelta = targetPosition.x - characterPosition.x;

        double angle = atan(yDelta / xDelta);

        int xLaunchPosition = characterPosition.x + (int)(abs(cos(angle) * hitBoxRadius) * signum(xDelta));
        int yLaunchPosition = characterPosition.y + (int)(abs(sin(angle) * hitBoxRadius) * signum(yDelta));

        return new Point(xLaunchPosition, yLaunchPosition);
    }

    public void dealDamage(IKillable character){
        character.reduceHealth(damage);
    }

    public float getSpeed(){
        return speed;
    }

    public void JustCollided(){
        hasCollided = true;
    }

    public boolean hasCollided(){
        return hasCollided;
    }
}
