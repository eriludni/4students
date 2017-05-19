package com.mygdx.game.Model;

import java.util.Random;

/**
 * Created by lucasr on 5/18/17.
 */
public class PowerUp implements IItem{
    private Random rand = new Random();
    private float xPos;
    private float yPos;
    private boolean toBeRemoved = false;
    private PowerUpModifier modifier;

    public PowerUp(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.modifier = new PowerUpModifier(this, rand.nextInt(3));
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

    public PowerUpModifier getModifier() {
        return modifier;
    }
}
