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
                System.out.println("Default map");
                break;
            case 1:
                modifier = new PlainMap();
                System.out.println("Plain map");
                break;
            case 2:
                modifier = new PlatformMap();
                System.out.println("Platform map");
                break;
            default:
                modifier = new DefaultMap();
                System.out.println("Default map");
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
