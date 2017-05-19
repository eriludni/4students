package com.mygdx.game.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Erik on 2017-05-03.
 */
public class Generator {

    private Random random = new Random();
    private int[][] mapArray;
    private final int row =20;
    private final int col = 40;
    private int[][] platformStartPoints = new int[3][2];
    private int[][] pitfallStartPoints = new int[3][2];
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 3;
    private int numberOfPlatforms = 3;
    private int platformLength = 3;
    private int minPlatformRow = 12; //Min 0
    private int maxPlatformRow = 15; //Max 19
    private int numberOfPitfalls = 2;
    private int pittfallLength = 2;
    private int minPitfallRow = 0; //Min 0
    private int maxPitfallRow = 20; //Max 20
    private PowerUp powerUp;
    private static Generator generatorInstance = null;


    private Generator() {
        this.mapArray = new int[row][col];
        setBasePoints();
        for (int x = 0; x < col - 1; x++) {
            growFromPoints(x);
        }
        placeGround();
        createPlatforms(numberOfPlatforms, platformLength);
        //createPitfalls(numberOfPitfalls, pittfallLength);
    }

    public void applyModifier() {
        if(powerUp != null) {
            pointsDistance = powerUp.getModifier().getPoinsDistance();
            mountainTop = powerUp.getModifier().getMountainTop();
            mountainDiff = powerUp.getModifier().getMountainDiff();
            numberOfPlatforms = powerUp.getModifier().getNumberOfPlatforms();
            platformLength = powerUp.getModifier().getPlatformLength();
            numberOfPitfalls = powerUp.getModifier().getNumberOfPitfalls();
            pittfallLength = powerUp.getModifier().getPitfallLength();
        }
    }

    public void setNextMapStructure(){
        int lastBasePoint = findRow(col - 1);
        clear(mapArray);
        applyModifier();
        setBasePointsFrom(lastBasePoint);
        for (int x = 0; x < col - 1; x++) {
            growFromPoints(x);
        }
        placeGround();
        createPlatforms(numberOfPlatforms, platformLength);
        createPitfalls(numberOfPitfalls, pittfallLength);
    }

    public void clear(int[][] array){
        for(int i = 0; i < col; i ++){
            for(int j = 0; j < row; j ++){
                array[j][i] = 0;
            }
        }
    }

    public void setBasePointsFrom(int lastBasePoint) {

        this.mapArray[lastBasePoint][0] = 1;

        for (int c = pointsDistance; c < col - pointsDistance; c = c + pointsDistance) {
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(col-1) == 0){
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][col-1] = 1;
        }
    }

    public void setBasePoints() {
        for (int c = 0; c < col - pointsDistance; c = c + pointsDistance) {
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(col-1) == 0){
            this.mapArray[random.nextInt(mountainDiff) + mountainTop][col-1] = 1;
        }
    }

    public void growFromPoints(int initValue) {
        int endValue = nextPointValue(initValue);
        int rowStart = findRow(initValue);
        if (rowStart < endValue) {
            int growCord = random.nextInt(2) + findRow(initValue);

            this.mapArray[growCord][initValue + 1] = 1;
        } else if (rowStart > endValue) {
            int growCord = random.nextInt(2) * -1 + findRow(initValue);
            this.mapArray[growCord][initValue + 1] = 1;
        } else {
            this.mapArray[findRow(initValue)][initValue+1] =1;

        }
    }

    public int nextPointValue(int currentColum) {
        for (int c = currentColum + 1; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (this.mapArray[r][c] == 1) {
                    return r;
                }
            }
        }
        return 0;
    }

    public int findRow(int colum) {
        for (int x = 0; x < row; x++) {
            if (this.mapArray[x][colum] == 1)
                return x;
        }
        return 0;
    }

    public void placeGround(){
        for(int c = 0; c < col; c++){
            for(int r = row-1; r >= 0; r--){
                if(this.mapArray[r][c] == 1){
                    break;
                }
                mapArray[r][c] = 2;
            }
        }
    }

    public void createPlatforms(int platforms, int length) {
        setPlatformStartPoints(platforms);
        for(int i = 0; i < platformStartPoints.length; i++) {
            int row = platformStartPoints[i][0];
            int startCol = platformStartPoints[i][1];
            for (int start = 0; start < length; start++) {
                mapArray[row][startCol] = 3;
                if(startCol < this.col - 7) {
                    startCol++;
                }
            }
        }
    }

    public void setPlatformStartPoints(int startPoints) {
        int i = 0;
        for(int row = minPlatformRow; row <= maxPlatformRow; row++) {
            if(i < startPoints) {
                int c = random.nextInt(this.col - 7);
                this.mapArray[row][c] = 3;
                platformStartPoints[i][0] = row;
                platformStartPoints[i][1] = c;
                i++;
            }
        }
    }

    public void createPitfalls(int pitfalls, int width) {
        setPitfallStartPoints(pitfalls);
        for(int i = 0; i < pitfallStartPoints.length; i++) {
            int startRow = pitfallStartPoints[i][0];
            int startCol = pitfallStartPoints[i][1];
            for(int length = 0; length < width; length++) {
                for(int depth = 0; depth < this.col; depth++) {
                    this.mapArray[startRow][startCol] = 0;
                    if(startRow < this.row - 1) {
                        startRow++;
                    }
                }
                startRow = pitfallStartPoints[i][0];
                if(startCol < this.col - 1) {
                    startCol++;
                }
            }
        }
    }

    public void setPitfallStartPoints(int startPoints) {
        for(int row = minPitfallRow; row < startPoints; row++) {
                int c = random.nextInt(this.col - 7);
                this.mapArray[mountainTop][c] = 0;
                pitfallStartPoints[row][0] = row;
                pitfallStartPoints[row][1] = c;
        }
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public static Generator getGeneratorInstance() {
        if(generatorInstance == null) {
            generatorInstance = new Generator();
        }
        return generatorInstance;
    }

    public static void resetGeneratorInstance() {
        generatorInstance = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMapArray(int x, int y) {
        return this.mapArray[y][x];
    }

}
