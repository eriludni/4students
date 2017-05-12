package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/12/17.
 */
public interface IKillable {
    void setToBeRemoved(boolean toBeRemoved);
    boolean getToBeRemoved();
    int getHealth();
    void setHealth(int health);
    void reduceHealth(int damageValue);
    boolean isDead();
    void checkDead();
    void setDead(boolean dead);
}
