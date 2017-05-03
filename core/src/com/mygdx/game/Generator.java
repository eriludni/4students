package com.mygdx.game;

import java.util.Random;

/**
 * Created by Erik on 2017-05-03.
 */
public class Generator {

    private int[][] mapArray;
    private final int row = 31;
    private final int col = 99;
    Random random = new Random();

    public Generator(){
        this.mapArray =  new int[row][col];
    }

    public void loopThroughMap(){
        for (int r = 0; r < row; r++){
            for ( int c = 0; c < col; c++){
                mapArray[r][c] = ;
            }
        }
    }


}
