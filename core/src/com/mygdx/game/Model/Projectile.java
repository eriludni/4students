package com.mygdx.game.Model;

import java.awt.*;
import java.awt.color.ICC_ColorSpace;

import static java.lang.Math.*;

/**
 * Created by Niklas on 2017-05-08.
 */
public class Projectile implements DynamicalBody{
    private float speed;
    private int damage;
    private boolean hasCollided;
    private double angle;
    private float xPos;
    private float yPos;
    private float x_velocity;
    private float y_velocity;

    public Projectile(float speed, int damage){
        this.speed = speed;
        this.damage = damage;
        hasCollided = false;
    }

    public static Point getLaunchPosition(Point characterPosition, Point targetPosition, float hitBoxRadius)
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

    @Override
    public void setxPos(float x) {
        xPos = x;
    }

    @Override
    public void setyPos(float y) {
        yPos = y;
    }

    @Override
    public void setX_velocity(float xVelocity) {
        x_velocity = xVelocity;
    }

    @Override
    public void setY_velocity(float yVelocity) {
        y_velocity = yVelocity;
    }

    @Override
    public float getXPos() {
        return xPos;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public float getX_velocity() {
        return x_velocity;
    }

    @Override
    public float getY_velocity() {
        return y_velocity;
    }
}
