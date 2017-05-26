package com.mygdx.game.Model;

/**
 * Created by Erik on 17/05/2017.
 */
public class PacingBehavior extends EnemyBehavior implements IEnemyBehavior {

    private boolean direction;
   public PacingBehavior(){
       direction = true;
   }



    /**
     *Getter
     */
    @Override
    public float getX_Velocity() {
        return super.getX_Velocity();
    }

    /**
     *Getter
     */
    @Override
    public float getY_Velocity() {
        return super.getY_Velocity();
    }

    /**
     * updates the x-velocity
     */

    @Override
    public float applyX_Velocity(float currentXV, float currentYV) {

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

    /**
     * updates the y-velocity
     */

    @Override
    public float applyY_Velocity(float currentXV, float currentYV) {
       return 0;

    }
}
