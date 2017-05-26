package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Model.Projectile;
import com.mygdx.game.Utils.CONSTANTS;

import java.awt.*;



/**
 * Created by Niklas on 2017-05-08.
 */
public class LibgdxProjectile implements Drawable, LibgdxTeleportable {
    private Body b2Body;
    private LibgdxWorld world = LibgdxWorld.getlgdxWorld();
    private Projectile projectileModel;
    private int textureKey = 3;
    private int fixtureRadius = 10;

    LibgdxProjectile(Point startPosition, Point targetPosition, Projectile projectileModel, float shooterWidth){
        this.projectileModel = projectileModel;
        Vector2 projectileVector = getDirectionVector(startPosition, targetPosition);
        projectileVector.setLength(this.projectileModel.getSpeed());

        Point projectileLaunchPoint = Projectile.getLaunchPosition(startPosition, targetPosition, shooterWidth * 50 + fixtureRadius + 2);
        Body projectileBody = makeBody(projectileLaunchPoint);
        projectileBody.setLinearVelocity(projectileVector);
    }

    /**
     *Calculates the direction of the bullet so that it goes towards targetPosition.
     */
    private Vector2 getDirectionVector(Point startPosition, Point targetPosition){
        float velocityX = targetPosition.x - startPosition.x;
        float velocityY = targetPosition.y - startPosition.y;
        return new Vector2(velocityX, velocityY);
    }

    /**
     *Creates the physical body of the projectile
     */
    private Body makeBody(Point startPosition){
        BodyDef bdef = new BodyDef();
        bdef.position.set( startPosition.x / CONSTANTS.PPM, startPosition.y / CONSTANTS.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / CONSTANTS.PPM);
        fdef.shape = shape;

        b2Body.createFixture(fdef);

        CircleShape sensor = new CircleShape();
        sensor.setRadius(10 / CONSTANTS.PPM);

        fdef.isSensor = true;
        fdef.shape = sensor;

        b2Body.createFixture(fdef);

        b2Body.setUserData(this);

        b2Body.setGravityScale(0);
        b2Body.setBullet(true);

        return b2Body;
    }

    /**
     * Creates a body using data from the projectile model.
     */
    public void createBodyFromModel(){
        int x = (int)projectileModel.getXPos();
        int y = (int)projectileModel.getYPos();
        makeBody(new Point(x,y));
        float vectorX = projectileModel.getX_velocity();
        float vectorY = projectileModel.getY_velocity();
        Vector2 vector2 = new Vector2(vectorX,vectorY);
        b2Body.setLinearVelocity(vector2);
    }

    /**
     *Getter
     */
    public Projectile getModel(){
        return projectileModel;
    }

    /**
     *Getter
     */
    boolean isSetForRemoval() {
        return projectileModel.hasCollided();
    }

    /**
     *Setter
     */
    void setForRemoval() {
        projectileModel.JustCollided();
    }

    /**
     *Getter
     */
    public int getBodyID() {
        return textureKey;
    }


    /**
     * Getter
     */
    public float getFixtureWidth(){
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }

    /**
     * Getter
     */
    public float getFixtureHeight(){
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }
}