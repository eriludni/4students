package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-27.
 */
public interface ICharacter {
    float getXPos();
    void setxPos(float xPos);
    float getYPos();
    void setyPos(float yPos);
    float getRadius();
    void setRadius(float radius);
    float getY_velocity();
    void setY_velocity(float y_velocity);
    void reverseYVelocity();
    float getX_velocity();
    void setX_velocity(float x_velocity);
    void reverseXVelocity();
    int getHealth();
    void setHealth(int health);
    void reduceHealth(int damageValue);
    boolean isDead();
    void checkDead();
    void setDead(boolean dead);
    boolean isAirBorn();
    void setAirBorn(boolean airBorn);
    void setToBeRemoved(boolean toBeRemoved);
}
