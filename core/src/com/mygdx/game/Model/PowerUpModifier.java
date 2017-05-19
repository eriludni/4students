package com.mygdx.game.Model;

/**
 * Created by lucasr on 5/18/17.
 */
public class PowerUpModifier implements IPowerUpModifier{
    private PowerUp powerUp;
    private IPowerUpModifier modifier;

    public PowerUpModifier(PowerUp powerUp, int id) {
        this.powerUp = powerUp;

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

    @Override
    public int getPoinsDistance() {
        return modifier.getPoinsDistance();
    }

    @Override
    public int getMountainTop() {
        return modifier.getMountainTop();
    }

    @Override
    public int getMountainDiff() {
        return modifier.getMountainDiff();
    }

    @Override
    public int getNumberOfPlatforms() {
        return modifier.getNumberOfPlatforms();
    }

    @Override
    public int getPlatformLength() {
        return modifier.getPlatformLength();
    }

    @Override
    public int getMinPlatformRow() {
        return modifier.getMinPlatformRow();
    }

    @Override
    public int getMaxPlatformRow() {
        return modifier.getMaxPlatformRow();
    }

    @Override
    public int getNumberOfPitfalls() {
        return modifier.getNumberOfPitfalls();
    }

    @Override
    public int getPitfallLength() {
        return modifier.getPitfallLength();
    }

    @Override
    public int getMinPitfallRow() {
        return modifier.getMinPitfallRow();
    }

    @Override
    public int getMaxPitfallRow() {
        return modifier.getMaxPitfallRow();
    }
}
