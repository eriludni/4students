package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 *
 * @author Lucas Ruud
 * Responsibility: Interface for items
 * Uses:
 * Used by: PowerUp
 */
public interface IItem {
    float getxPos();
    float getyPos();
    boolean getToBeRemoved();
    void setxPos(float xPos);
    void setyPos(float yPos);
    void setToBeRemoved(boolean toBeRemoved);
}
