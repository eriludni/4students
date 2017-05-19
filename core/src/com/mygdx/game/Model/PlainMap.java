package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 */
public class PlainMap implements IPowerUpModifier{
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 1;
    private int numberOfPlatforms = 0;
    private int platformLength = 0;
    private int numberOfPitfalls = 0;
    private int pittfallLength = 0;


    @Override
    public int getPoinsDistance() {
        return this.pointsDistance;
    }

    @Override
    public int getMountainTop() {
        return this.mountainTop;
    }

    @Override
    public int getMountainDiff() {
        return this.mountainDiff;
    }

    @Override
    public int getNumberOfPlatforms() {
        return this.numberOfPlatforms;
    }

    @Override
    public int getPlatformLength() {
        return this.platformLength;
    }

    @Override
    public int getNumberOfPitfalls() {
        return this.numberOfPitfalls;
    }

    @Override
    public int getPitfallLength() {
        return this.pittfallLength;
    }
}
