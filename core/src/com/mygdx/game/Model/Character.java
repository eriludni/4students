package com.mygdx.game.Model;

/**
 * Created by Lucas on 2017-04-27.
 */
public abstract class Character implements ICharacter{

    protected int health;
    protected float xPos;
    protected float yPos;
    protected float radius;
    protected float x_velocity;
    protected float y_velocity;
    protected boolean dead;
    protected boolean airBorn;
    protected boolean toBeRemoved;

    @Override
    public float getXPos() {
        return xPos;
    }

    @Override
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getY_velocity() {
        return y_velocity;
    }

    @Override
    public void setY_velocity(float y_velocity) {
        this.y_velocity = y_velocity;
    }

    @Override
    public void reverseYVelocity() {
        setY_velocity(-y_velocity);
    }

    @Override
    public float getX_velocity() {
        return x_velocity;
    }

    @Override
    public void setX_velocity(float x_velocity) {
        this.x_velocity =x_velocity;
    }

    @Override
    public void reverseXVelocity() {
        setX_velocity(-x_velocity);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void reduceHealth(int damageValue) {
        setHealth(health - damageValue);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void checkDead() {
        if (isDead()) {
            setDead(true);
            setToBeRemoved(true);
        }
    }

    @Override
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean isAirBorn() {
        return airBorn;
    }

    @Override
    public void setAirBorn(boolean airBorn) {
        this.airBorn = airBorn;
    }

    @Override
    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }


    /*
    protected Body b2body;
    protected World world;

    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / Dash.PPM, character.getYPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = character.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(character.getRadius() / Dash.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef);
    }
    */
}
