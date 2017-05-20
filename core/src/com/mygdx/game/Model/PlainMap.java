package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 */
public class PlainMap implements IPowerUpModifier{
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 2;
    private int numberOfPlatforms = 0;
    private int platformLength = 0;
    private int minPlatformRow = 12; //Min 0
    private int maxPlatformRow = 15; //Max 20
    private int numberOfPitfalls = 0;
    private int pittfallLength = 0;
    private int minPitfallRow = 0; //Min 0
    private int maxPitfallRow = 20; //Max 20



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
    public int getMinPlatformRow() {
        return this.minPlatformRow;
    }

    @Override
    public int getMaxPlatformRow() {
        return this.maxPlatformRow;
    }

    @Override
    public int getNumberOfPitfalls() {
        return this.numberOfPitfalls;
    }

    @Override
    public int getPitfallLength() {
        return this.pittfallLength;
    }

    @Override
    public int getMinPitfallRow() {
        return this.minPitfallRow;
    }

    @Override
    public int getMaxPitfallRow() {
        return this.maxPitfallRow;
    }
}
