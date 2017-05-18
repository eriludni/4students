package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 */
public class PowerUp {
    private float xPos;
    private float yPos;
    private boolean toBeRemoved = false;

    public PowerUp(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public boolean getToBeRemoved() {
        return toBeRemoved;
    }
}
