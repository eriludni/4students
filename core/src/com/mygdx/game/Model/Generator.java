package com.mygdx.game.Model;

import java.util.Random;

/**
 * Created by Erik on 2017-05-03.
 */
public class Generator {

    private int[][] mapArray;
    private final int row = 19;
    private final int col = 99;
    private Random random = new Random();
    private int randNumber;
    private int[][] cleanMapArray;
    private int rek = 5;
    private int test = 0;

    public Generator() {
        this.mapArray = new int[row][col];
        this.cleanMapArray = new int[row][col];
    }

    public void loopThroughMap() {
        randNumber = random.nextInt(99);
        for (int c = 0; c < col-6; c=c+6) {
            mapArray[random.nextInt(4) + 14][c] = 1;
            System.out.println();
        }
    }

    public void cleanUp() {

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (r < 11) {
                    cleanMapArray[r][c] = 0;
                }
                if (11 <= r && r < 16) {
                    if (c - 1 > 0 && c + 1 < col) {
                        if (mapArray[r - 1][c - 1] + mapArray[r - 1][c] + mapArray[r - 1][c + 1] + mapArray[r][c - 1] + mapArray[r][c + 1] > 3) {
                            cleanMapArray[r][c] = 1;
                        }
                        if (mapArray[r - 1][c - 1] + mapArray[r - 1][c] + mapArray[r - 1][c + 1] + mapArray[r][c - 1] + mapArray[r][c + 1] < 1) {
                            cleanMapArray[r][c] = 0;
                        }

                    }
                }
                if (r >= 16) {
                    cleanMapArray[r][c] = 1;
                }
                System.out.print(cleanMapArray[r][c]);
            }
            System.out.println();
        }
        //testing a bit of recursion
       /* if (--rek > 0) {
            mapArray = cleanMapArray;
            System.out.println();
            cleanUp();
        }
        */
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getMapArray(int x, int y){
        return this.mapArray[x][y];
    }

}
