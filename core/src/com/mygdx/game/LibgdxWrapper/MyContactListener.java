package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Generator;

import java.util.ArrayList;

/**
 * Created by Niklas on 2017-05-09.
 */
public class MyContactListener implements ContactListener {
    private World world;

    private LibgdxProjectile lgdxProjectile;
    private LibgdxWorld lgdxWorld = LibgdxWorld.getlgdxWorld();
    private LibgdxPlayer lgdxPlayer = lgdxWorld.getPlayerCharacter();
    private ArrayList<LibgdxEnemy> lgdxEnemies = lgdxWorld.getEnemyCharacters();
    private LibgdxEnemy lgdxEnemy;
    private Generator generator = Generator.getGeneratorInstance();

    public MyContactListener(World world){
        this.world = world;
    }

    /**
     * The method that is called whenever two objects begins to collide with each other
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getBody().isBullet()){
            LibgdxProjectile userdata = (LibgdxProjectile) fixtureA.getBody().getUserData();
            userdata.setForRemoval();
        }
        if(fixtureB.getBody().isBullet()){
            LibgdxProjectile userdata = (LibgdxProjectile) fixtureB.getBody().getUserData();
            userdata.setForRemoval();
        }

        if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer || fixtureB.getBody().getUserData() instanceof LibgdxPlayer) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer && fixtureB.getBody().getUserData() instanceof LibgdxEnemy) {
                lgdxPlayer.getModel().reduceHealth(1);
            }
            else if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().getUserData() instanceof LibgdxPlayer) {
                lgdxPlayer.getModel().reduceHealth(1);
            }
        }

        if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy || fixtureB.getBody().getUserData() instanceof LibgdxEnemy) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && lgdxEnemies.contains(fixtureA.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
            }
            else if(fixtureB.getBody().getUserData() instanceof LibgdxEnemy && lgdxEnemies.contains(fixtureB.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
            }

            lgdxEnemy.getModel().setX_velocity(0);


        }
        if(fixtureA.getBody().isBullet() || fixtureB.getBody().isBullet()) {
            if((fixtureA.getBody().isBullet() && fixtureB.getBody().getUserData() instanceof LibgdxEnemy) || (fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().isBullet())) {
                if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().isBullet()) {
                    lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
                    lgdxProjectile = (LibgdxProjectile) fixtureB.getBody().getUserData();
                }
                else if(fixtureB.getBody().getUserData() instanceof LibgdxEnemy && fixtureA.getBody().isBullet()) {
                    lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
                    lgdxProjectile = (LibgdxProjectile) fixtureA.getBody().getUserData();
                }
                lgdxProjectile.getModel().dealDamage(lgdxEnemy.getModel());
            }

        }
        if(fixtureA.getBody().getUserData() instanceof LibgdxProjectile || fixtureB.getBody().getUserData() instanceof LibgdxPowerUp) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer && fixtureB.getBody().getUserData() instanceof LibgdxPowerUp) {
                generator.setPowerUp(((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp());
                ((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp().setToBeRemoved(true);
            }
            else if(fixtureB.getBody().getUserData() instanceof LibgdxPlayer && fixtureA.getBody().getUserData() instanceof LibgdxPowerUp){
                generator.setPowerUp(((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp());
                ((LibgdxPowerUp) fixtureA.getBody().getUserData()).getLogicalPowerUp().setToBeRemoved(true);
            }
        }

    }

    /**
     *  The method that is called whenever two objects ends a collision with each other
     */
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void setLgdxEnemies(ArrayList<LibgdxEnemy> lgdxEnemies) {
        this.lgdxEnemies = lgdxEnemies;
    }
}
