package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Generator;

import java.util.ArrayList;

/**
 * Created by Niklas on 2017-05-09.
 */
public class MyContactListener implements ContactListener {
    private World world;

    private LibgdxWorld lgdxWorld = LibgdxWorld.getlgdxWorld();
    private LibgdxPlayer lgdxPlayer = lgdxWorld.getPlayerCharacter();
    private ArrayList<LibgdxEnemy> lgdxEnemies = lgdxWorld.getEnemyCharacters();
    private LibgdxEnemy lgdxEnemy;
    private Generator generator = Generator.getGeneratorInstance();

    public MyContactListener(World world){
        this.world = world;
    }

    /*
    The method that is called whenever two objects begins to collide with each other
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body body = fixtureA.getBody();

        //System.out.println(fixtureA.getShape().getType().equals(fixtureB.getShape().getType()));
        if(fixtureA.getBody().isBullet()){
            //LibgdxBodyUserdata userdata = (LibgdxBodyUserdata) fixtureA.getBody().getUserData();
            //userdata.isSetForRemoval = true;

            LibgdxProjectile userdata = (LibgdxProjectile) fixtureA.getBody().getUserData();
            userdata.setForRemoval();
        }
        if(fixtureB.getBody().isBullet()){
            //LibgdxBodyUserdata userdata = (LibgdxBodyUserdata) fixtureB.getBody().getUserData();
            //userdata.isSetForRemoval = true;

            LibgdxProjectile userdata = (LibgdxProjectile) fixtureB.getBody().getUserData();
            userdata.setForRemoval();
        }

        if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer || fixtureB.getBody().getUserData() instanceof LibgdxPlayer) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer && fixtureB.getBody().getUserData() instanceof LibgdxEnemy) {
                lgdxPlayer.getModel().reduceHealth(1);
                System.out.println(lgdxPlayer.getModel().getHealth());
                System.out.println("contact_1");
            }
            else if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().getUserData() instanceof LibgdxPlayer) {
                lgdxPlayer.getModel().reduceHealth(1);
                System.out.println(lgdxPlayer.getModel().getHealth());
                System.out.println("contact_2");
            }
        }

        if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy || fixtureB.getBody().getUserData() instanceof LibgdxEnemy) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && lgdxEnemies.contains(fixtureA.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
            }
            else if(fixtureB.getBody().getUserData() instanceof LibgdxEnemy && lgdxEnemies.contains(fixtureB.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
            }
            /*
            if(lgdxEnemies.contains(fixtureA.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
            }
            else if(lgdxEnemies.contains(fixtureB.getBody().getUserData())) {
                lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
            }
            */

            lgdxEnemy.getModel().setX_velocity(0);


        }
        if(fixtureA.getBody().isBullet() || fixtureB.getBody().isBullet()) {
            if((fixtureA.getBody().isBullet() && fixtureB.getBody().getUserData() instanceof LibgdxEnemy) || (fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().isBullet())) {
                if(fixtureA.getBody().getUserData() instanceof LibgdxEnemy && fixtureB.getBody().isBullet()) {
                    lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
                }
                else if(fixtureB.getBody().getUserData() instanceof LibgdxEnemy && fixtureA.getBody().isBullet()) {
                    lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
                }
                /*
                if(lgdxEnemies.contains(fixtureA.getBody().getUserData())) {
                    lgdxEnemy = (LibgdxEnemy) fixtureA.getBody().getUserData();
                }
                else if(lgdxEnemies.contains(fixtureB.getBody().getUserData())) {
                    lgdxEnemy = (LibgdxEnemy) fixtureB.getBody().getUserData();
                }
                */

                lgdxEnemy.getModel().reduceHealth(1);

                System.out.println(lgdxEnemy.getModel().getHealth());
                System.out.println(lgdxEnemy.getB2Body().getUserData());
                System.out.println("contact_3");
            }

        }
        if(fixtureA.getBody().getUserData() instanceof LibgdxProjectile || fixtureB.getBody().getUserData() instanceof LibgdxPowerUp) {
            if(fixtureA.getBody().getUserData() instanceof LibgdxPlayer && fixtureB.getBody().getUserData() instanceof LibgdxPowerUp) {
                System.out.println("Contact_powerUp");
                generator.setPowerUp(((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp());
                ((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp().setToBeRemoved(true);
            }
            else if(fixtureB.getBody().getUserData() instanceof LibgdxPlayer && fixtureA.getBody().getUserData() instanceof LibgdxPowerUp){
                System.out.println("Contact_powerUp2");
                generator.setPowerUp(((LibgdxPowerUp) fixtureB.getBody().getUserData()).getLogicalPowerUp());
                ((LibgdxPowerUp) fixtureA.getBody().getUserData()).getLogicalPowerUp().setToBeRemoved(true);
            }
        }

    }

    /*
    The method that is called whenever two objects ends a collision with each other
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
