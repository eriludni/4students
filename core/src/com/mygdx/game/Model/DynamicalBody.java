package com.mygdx.game.Model;

/**
 * Created by Niklas on 2017-05-18.
 */
public interface DynamicalBody {
    public void setxPos(float x);
    public void setyPos(float y);
    public void setX_velocity(float xVelocity);
    public void setY_velocity(float yVelocity);
    public float getXPos();
    public float getYPos();
    public float getX_velocity();
    public float getY_velocity();

}
