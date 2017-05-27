package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-27.
 *
 * @author Lucas Ruud
 * Responsibility: Handles general functions that all characters should have
 * Uses: IKillable, Teleportable
 * Used by: Enemy, Player, LibgdxCharacter, LibgdxEnemy, LibgdxPlayer
 */
public abstract class Character implements IKillable, Teleportable {
    protected int health;
    protected float xPos;
    protected float yPos;
    protected float radius;
    protected float x_velocity;
    protected float y_velocity;
    protected boolean dead;
    protected boolean airBorn;
    protected boolean toBeRemoved;

    private float maxVelocity = 2;
    private float minVelocity = -2;
    private float horizontalAcceleration = 0.1f;
    private float verticalAcceleration = 6f;

    /**
     *Getter
     */
    @Override
    public float getXPos() {
        return xPos;
    }

    /**
     *Setter
     */
    @Override
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     *Getter
     */
    @Override
    public float getYPos() {
        return yPos;
    }

    /**
     *Setter
     */
    @Override
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     *Getter
     */
    public float getRadius() {
        return radius;
    }

    /**
     *Setter
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     *Getter
     */
    @Override
    public float getY_velocity() {
        return y_velocity;
    }

    /**
     *Setter
     */
    @Override
    public void setY_velocity(float y_velocity) {
        this.y_velocity = y_velocity;
    }

    /**
     *Sets the Y velocity to be negative of its current value
     */
    public void reverseYVelocity() {
        setY_velocity(-y_velocity);
    }

    /**
     *Getter
     */
    @Override
    public float getX_velocity() {
        return x_velocity;
    }

    /**
     *Setter
     */
    @Override
    public void setX_velocity(float x_velocity) {
        this.x_velocity =x_velocity;
    }

    /**
     *Sets the X velocity to be negative of its current value
     */
    public void reverseXVelocity() {
        setX_velocity(-x_velocity);
    }

    /**
     *Getter
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     *Setter
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *Reduces the health of a character by the specified damage
     */
    @Override
    public void reduceHealth(int damageValue) {
        setHealth(health - damageValue);
    }

    /**
     *Getter
     */
    @Override
    public boolean isDead() {
        return dead;
    }

    /**
     *Checks if a characters health is less than or equal to 0 and sets the dead variable to true if it is
     */
    @Override
    public void checkDead() {
        if (health <= 0) {
            setDead(true);
        }
    }

    /**
     *Setter
     */
    @Override
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     *Getter
     */

    public boolean isAirBorn() {
        return airBorn;
    }

    /**
     *Setter
     */
    public void setAirBorn(boolean airBorn) {
        this.airBorn = airBorn;
    }

    /**
     *Checks if a characters Y position is less than or equal to -5 and sets the dead variable to true if it is
     */
    public void checkOutOfBounds() {
        if(yPos <= -5) {
            setDead(true);
        }
    }

    /**
     *Setter
     */
    @Override
    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    /**
     *Getter
     */
    @Override
    public boolean getToBeRemoved() {
        return toBeRemoved;
    }

    /**
     *Getter
     */
    public float getMaxVelocity() {
        return maxVelocity;
    }

    /**
     *Getter
     */
    public float getMinVelocity() {
        return minVelocity;
    }

    /**
     *Getter
     */
    public float getHorizontalAcceleration() {
        return horizontalAcceleration;
    }

    /**
     *Getter
     */
    public float getVerticalAcceleration() {
        return verticalAcceleration;
    }
}
