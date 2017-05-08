package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_enemy extends libgdx_character{

    public libgdx_enemy(Enemy enemy) {
        this.character = enemy;
        defineCharacter(character);
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
    }

    public void moveEnemyUp(float y) {
        if(getEnemyLinearYVelocity() == 0) {
            getB2Body().applyLinearImpulse(new Vector2(0, y), getB2Body().getWorldCenter(),true);
        }
    }

    public void moveEnemyRight(float x) {
        getB2Body().applyLinearImpulse(new Vector2(x, 0), getB2Body().getWorldCenter(),true);
    }

    public void moveEnemyLeft(float x) {
        getB2Body().applyLinearImpulse(new Vector2(-x, 0), getB2Body().getWorldCenter(),true);
    }

    public float getEnemyLinearYVelocity() {
        return getB2Body().getLinearVelocity().y;
    }

    public float getEnemyLinearXVelocity() {
        return getB2Body().getLinearVelocity().x;
    }

    public void setEnemyLinearXVelocity(float x) {
        getB2Body().setLinearVelocity(x, getB2Body().getLinearVelocity().y);
    }

    public void setEnemyLinearYVelocity(float y) {
        getB2Body().setLinearVelocity(getB2Body().getLinearVelocity().x, y);
    }
}
