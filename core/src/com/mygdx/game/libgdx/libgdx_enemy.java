package com.mygdx.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.EnemyBrain;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.IKillable;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_enemy extends libgdx_character{
    private Enemy enemyModel;
    private EnemyBrain brainModel;

    public libgdx_enemy(Enemy enemy) {
        enemyModel = enemy;
        brainModel = enemy.getBrain();
        defineCharacter(enemyModel);

        System.out.println("libgdx enemy created");
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
    Move the enemy upwards with a specified force
     */
    public void moveEnemyUp(float y) {
        if(getLinearYVelocity() == 0) {
            getB2Body().setLinearVelocity(getLinearXVelocity(), y);
        }
    }

    /*
    Move the enemy rightwards with a specified force
     */
    public void moveEnemy(float[] v) {
        getB2Body().setLinearVelocity(v[0], v[1]);
    }


    public void dispose() {
        this.dispose();
    }

    /*
    Checks if the enemy has died
     */
    public void update(float dt) {
        enemyModel.checkDead();
        moveEnemy(this.brainModel.updateVelocity());
        if(enemyModel.isDead()) {
            //System.out.println("Enemy died");
        }
    }

    public Enemy getEnemyModel() {
        return enemyModel;
    }
}
