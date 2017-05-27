package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 *
 * @author Lucas Ruud
 * Responsibility:
 * Uses: IPowerUpModifier
 * Used by: PowerUp
 */
public class PowerUpModifier implements IPowerUpModifier{
    private IPowerUpModifier modifier;

    public PowerUpModifier(int id) {

        switch(id) {
            case 0:
                modifier = new DefaultMap();
                break;
            case 1:
                modifier = new PlainMap();
                break;
            case 2:
                modifier = new PlatformMap();
                break;
            default:
                modifier = new DefaultMap();
                break;
        }
    }

    /**
     *Getter
     */
    @Override
    public int getPoinsDistance() {
        return modifier.getPoinsDistance();
    }

    /**
     *Getter
     */
    @Override
    public int getMountainTop() {
        return modifier.getMountainTop();
    }

    /**
     *Getter
     */
    @Override
    public int getMountainDiff() {
        return modifier.getMountainDiff();
    }

    /**
     *Getter
     */
    @Override
    public int getNumberOfPlatforms() {
        return modifier.getNumberOfPlatforms();
    }

    /**
     *Getter
     */
    @Override
    public int getPlatformLength() {
        return modifier.getPlatformLength();
    }

    /**
     *Getter
     */
    @Override
    public int getMinPlatformRow() {
        return modifier.getMinPlatformRow();
    }

    /**
     *Getter
     */
    @Override
    public int getMaxPlatformRow() {
        return modifier.getMaxPlatformRow();
    }

    /**
     *Getter
     */
    @Override
    public int getNumberOfPitfalls() {
        return modifier.getNumberOfPitfalls();
    }

    /**
     *Getter
     */
    @Override
    public int getPitfallLength() {
        return modifier.getPitfallLength();
    }

    /**
     *Getter
     */
    @Override
    public int getMinPitfallRow() {
        return modifier.getMinPitfallRow();
    }

    /**
     *Getter
     */
    @Override
    public int getMaxPitfallRow() {
        return modifier.getMaxPitfallRow();
    }
}
