package com.mygdx.game.Model;

import java.awt.*;

import static java.lang.Math.*;

/**
 * Created by Niklas on 2017-05-08.
 *
 * @author Niklas Baerveldt
 * Responsibility: Handles the data for projectiles in the game
 * Uses: Teleportable, IKillable
 * Used by: LibgdxProjectile, LibgdxCharacter
 */
public class Projectile implements Teleportable {
    private float speed;
    private int damage;
    private boolean hasCollided;
    private float xPos;
    private float yPos;
    private float x_velocity;
    private float y_velocity;

    public Projectile(float speed, int damage){
        this.speed = speed;
        this.damage = damage;
        hasCollided = false;
    }

    /**
     * Calculates the launchpoint of the projectile so that it appears that the it is shot from the center of the player character.
     */
    public static Point getLaunchPosition(Point characterPosition, Point targetPosition, float distanceFromShooterBody)
    {
        double yDelta = targetPosition.y - characterPosition.y;
        double xDelta = targetPosition.x - characterPosition.x;

        double angle = atan(yDelta / xDelta);

        int xLaunchPosition = characterPosition.x + (int)(abs(cos(angle) * distanceFromShooterBody) * signum(xDelta));
        int yLaunchPosition = characterPosition.y + (int)(abs(sin(angle) * distanceFromShooterBody) * signum(yDelta));

        return new Point(xLaunchPosition, yLaunchPosition);
    }

    public void dealDamage(IKillable character){
        character.reduceHealth(damage);
    }

    /**
     *Getter
     */
    public float getSpeed(){
        return speed;
    }

    /**
     *Setter
     */
    public void JustCollided(){
        hasCollided = true;
    }

    /**
     *Getter
     */
    public boolean hasCollided(){
        return hasCollided;
    }

    /**
     *Setter
     */
    @Override
    public void setxPos(float x) {
        xPos = x;
    }

    /**
     *Setter
     */
    @Override
    public void setyPos(float y) {
        yPos = y;
    }

    /**
     *Setter
     */
    @Override
    public void setX_velocity(float xVelocity) {
        x_velocity = xVelocity;
    }

    /**
     *Setter
     */
    @Override
    public void setY_velocity(float yVelocity) {
        y_velocity = yVelocity;
    }

    /**
     *Getter
     */
    @Override
    public float getXPos() {
        return xPos;
    }

    /**
     *Getter
     */
    @Override
    public float getYPos() {
        return yPos;
    }

    /**
     *Getter
     */
    @Override
    public float getX_velocity() {
        return x_velocity;
    }

    /**
     *Getter
     */
    @Override
    public float getY_velocity() {
        return y_velocity;
    }
}
