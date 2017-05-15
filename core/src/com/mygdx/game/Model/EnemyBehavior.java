package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class EnemyBehavior implements IEnemyBehavior {

    EnemyBehavior behavior;


    public EnemyBehavior(){}


    public EnemyBehavior(int id){
        switch (id){
            case 0:
                this.behavior = new LinearBehavior();
                break;
            case 1:
                break;
            case 2:
                break;
        }


    }


    public float getX_Velocity() {
        return 0;
    }
}
