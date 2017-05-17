package com.mygdx.game.Model;

/**
 * Created by Erik on 16/05/2017.
 */
public class ChasingBehavior extends EnemyBehavior implements IEnemyBehavior {


        float X_Velocity;
        float Y_Velocity;



        public ChasingBehavior(){

            this.X_Velocity = 0;
            this.Y_Velocity = 0;
        }


        public float getVelocity() {
            return X_Velocity;
        }


        @Override
        public float UpdateX_Velocity(float currentXV){
            if(currentXV <= 0){
                return 3f;
            }
            System.out.println("NÅGOT ÄR FEL");
            return 0;
        }

        public float UpdateY_Velocity(float currentYV, float currentXV ){
            if(currentXV == 0){
                return 4f;
            }
            return 0;
        }
}
