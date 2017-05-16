package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.IKillable;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_enemy extends libgdx_character{
    private Enemy enemyModel;

    public libgdx_enemy(Enemy enemy) {
        enemyModel = enemy;
        defineCharacter(enemyModel);
    }

    /*
    Applies a Body to the enemy, by using the method defineCharacter() in its super class and sets its userdata to itself, so it can be identified later
     */
    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
        getB2Body().setUserData(this);
    }

    /*
    Set the linear velocity in the x-axis for the enemy
     */
    public void setEnemyLinearXVelocity(float x) {
        getB2Body().setLinearVelocity(x, getLinearYVelocity());
    }

    /*
    Set the linear velocity in the y-axis for the enemy
     */
    public void setEnemyLinearYVelocity(float y) {
        getB2Body().setLinearVelocity(getLinearXVelocity(), y);
    }

    public void dispose() {
        this.dispose();
    }

    /*
    Checks if the enemy has died
     */
    public void update(float dt) {
        enemyModel.checkDead();

        if(enemyModel.isDead()) {
            //System.out.println("Enemy died");
        }
    }

    public Enemy getEnemyModel() {
        return enemyModel;
    }
}
