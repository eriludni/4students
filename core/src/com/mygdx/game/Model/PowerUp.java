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
        this.modifier = new PowerUpModifier(rand.nextInt(3));
    }

    /**
     *Getter
     */
    @Override
    public float getxPos() {
        return xPos;
    }

    /**
     *Getter
     */
    @Override
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     *Getter
     */
    @Override
    public float getyPos() {
        return yPos;
    }

    /**
     *Getter
     */
    @Override
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     *Getter
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
    public PowerUpModifier getModifier() {
        return modifier;
    }
}
