package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 */
public class PowerUp implements IItem{
    private float xPos;
    private float yPos;
    private boolean toBeRemoved = false;
    private int modifier;

    public PowerUp(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.modifier = 0;
    }

    @Override
    public float getxPos() {
        return xPos;
    }

    @Override
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    @Override
    public float getyPos() {
        return yPos;
    }

    @Override
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    @Override
    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    @Override
    public boolean getToBeRemoved() {
        return toBeRemoved;
    }
}
