package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/19/17.
 *
 * @author Lucas Ruud
 * Responsibility: Handles how a platform map should be generated
 * Uses: IPowerUpModifier
 * Used by: PowerUpModifier
 */
public class PlatformMap implements IPowerUpModifier {
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 3;
    private int numberOfPlatforms = 3;
    private int platformLength = 7;
    private int minPlatformRow = 12;
    private int maxPlatformRow = 15;
    private int numberOfPitfalls = 3;
    private int pittfallLength = 4;
    private int minPitfallRow = 0;
    private int maxPitfallRow = 20;

    /**
     *Getter
     */
    @Override
    public int getPoinsDistance() {
        return this.pointsDistance;
    }

    /**
     *Getter
     */
    @Override
    public int getMountainTop() {
        return this.mountainTop;
    }

    /**
     *Getter
     */
    @Override
    public int getMountainDiff() {
        return this.mountainDiff;
    }

    /**
     *Getter
     */
    @Override
    public int getNumberOfPlatforms() {
        return this.numberOfPlatforms;
    }

    /**
     *Getter
     */
    @Override
    public int getPlatformLength() {
        return this.platformLength;
    }

    /**
     *Getter
     */
    @Override
    public int getMinPlatformRow() {
        return minPlatformRow;
    }

    /**
     *Getter
     */
    @Override
    public int getMaxPlatformRow() {
        return maxPlatformRow;
    }

    /**
     *Getter
     */
    @Override
    public int getNumberOfPitfalls() {
        return this.numberOfPitfalls;
    }

    /**
     *Getter
     */
    @Override
    public int getPitfallLength() {
        return this.pittfallLength;
    }

    /**
     *Getter
     */
    @Override
    public int getMinPitfallRow() {
        return minPitfallRow;
    }

    /**
     *Getter
     */
    @Override
    public int getMaxPitfallRow() {
        return maxPitfallRow;
    }
}
