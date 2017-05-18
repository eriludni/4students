package com.mygdx.game.Model;

/**
 * Created by Erik on 17/05/2017.
 */
public class PacingBehavior extends EnemyBehavior implements IEnemyBehavior {

    private boolean direction;
   public PacingBehavior(){
       direction = true;
   }



    @Override
    public float getX_Velocity() {
        return super.getX_Velocity();
    }

    @Override
    public float getY_Velocity() {
        return super.getY_Velocity();
    }

    @Override
    public float ApplyX_Velocity(float currentXV, float currentYV) {

       if(currentXV == 0){
           direction = !direction;
       }
       if(direction){
           if(currentXV > -1.5){
               return -0.2f;
           }
       }
       else{
           if(currentXV < 1.5){
               return 0.2f;
           }
       }
       return 0;
    }

    @Override
    public float ApplyY_Velocity(float currentXV, float currentYV) {
       return 0;

    }
}
