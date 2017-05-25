package com.mygdx.game.Model;

/**
 * Created by Niklas on 2017-05-18.
 */
public interface DynamicalBody {
    void setxPos(float x);
    void setyPos(float y);
    void setX_velocity(float xVelocity);
    void setY_velocity(float yVelocity);
    float getXPos();
    float getYPos();
    float getX_velocity();
    float getY_velocity();

}
