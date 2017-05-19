package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/19/17.
 */
public class PlatformMap implements IPowerUpModifier {
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 3;
    private int numberOfPlatforms = 3;
    private int platformLength = 6;
    private int minPlatformRow = 12; //Min 0
    private int maxPlatformRow = 15; //Max 19
    private int numberOfPitfalls = 3;
    private int pittfallLength = 6;
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
        return minPlatformRow;
    }

    @Override
    public int getMaxPlatformRow() {
        return maxPlatformRow;
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
        return minPitfallRow;
    }

    @Override
    public int getMaxPitfallRow() {
        return maxPitfallRow;
    }
}
