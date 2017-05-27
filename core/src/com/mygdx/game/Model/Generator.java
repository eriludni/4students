package com.mygdx.game.Model;


import java.util.Random;

/**
 * Created by Erik on 2017-05-03.
 *
 * @author Erik Lundin
 * Uses: PowerUp
 * Used by: LibgdxWorld, MyContactListener
 */
public class Generator {

    private Random random = new Random();
    private int[][] mapMatrix;
    private final int rowNumber =20;
    private final int colNumber = 40;
    private final int lastCol = colNumber - 1;
    private int[][] platformStartPoints = new int[3][2];
    private int[][] pitfallStartPoints = new int[3][2];
    private int[][] cloudStartPoints = new int[5][2];
    private int pointsDistance = 4;
    private int mountainTop = 17;
    private int mountainDiff = 3;
    private int numberOfPlatforms = 0;
    private int platformLength = 0;
    private int minPlatformRow = 12;
    private int maxPlatformRow = 15;
    private int numberOfPitfalls = 0;
    private int pittfallLength = 0;
    private int minPitfallRow = 0;
    private int maxPitfallRow = 20;
    private int minCloudRow = 1;
    private int maxCloudRow = 6;
    private int numberOfClouds = 5;
    private int cloudLength = 2;
    private PowerUp powerUp;
    private static Generator generatorInstance = null;


    private Generator() {
        this.mapMatrix = new int[rowNumber][colNumber];
        setBasePoints();
        for (int x = 0; x < lastCol; x++) {
            growFromPoints(x);
        }
        placeGround();
        createPlatforms(numberOfPlatforms, platformLength);
        creatCloud(numberOfClouds,cloudLength);
    }

    /**
     *Modifies the Generator variables according to the last PowerUp collected
     */

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

    /**
     * Generates a new mapmodel for the next structuresegment in the game
     */

    public void setNextMapStructure(){
        int lastBasePoint = findRow(lastCol);
        clear(mapMatrix);
        applyModifier();
        setBasePointsFrom(lastBasePoint);
        for (int x = 0; x < lastCol; x++) {
            growFromPoints(x);
        }
        placeGround();
        createPlatforms(numberOfPlatforms, platformLength);
        createPitfalls(numberOfPitfalls, pittfallLength);
        creatCloud(numberOfClouds,cloudLength);
    }

    /**
     *Clears the entire map array by setting all cells in the array to 0
     */
    public void clear(int[][] array){
        for(int i = 0; i < colNumber; i ++){
            for(int j = 0; j < rowNumber; j ++){
                array[j][i] = 0;
            }
        }
    }

    /**
     * Sets basepoints in the matrix with the distance pointsDistance apart.
     */

    public void setBasePoints() {
        for (int c = 0; c < colNumber - pointsDistance; c = c + pointsDistance) {
            this.mapMatrix[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(colNumber -1) == 0){
            this.mapMatrix[random.nextInt(mountainDiff) + mountainTop][colNumber -1] = 1;
        }
    }

    /**
     * Sets basepoints with the last basepoints of the last model as the first basepoints of the new model.
     */

    public void setBasePointsFrom(int lastBasePoint) {

        this.mapMatrix[lastBasePoint][0] = 1;

        for (int c = pointsDistance; c < colNumber - pointsDistance; c = c + pointsDistance) {
            this.mapMatrix[random.nextInt(mountainDiff) + mountainTop][c] = 1;
        }
        if(findRow(colNumber -1) == 0){
            this.mapMatrix[random.nextInt(mountainDiff) + mountainTop][colNumber -1] = 1;
        }
    }

    /**
     * Binds the basepoints in the matrix together.
     */
    public void growFromPoints(int initValue) {
        int endValue = nextPointValue(initValue);
        int rowStart = findRow(initValue);
        if (rowStart < endValue) {
            int growCord = random.nextInt(2) + findRow(initValue);

            this.mapMatrix[growCord][initValue + 1] = 1;
        } else if (rowStart > endValue) {
            int growCord = random.nextInt(2) * -1 + findRow(initValue);
            this.mapMatrix[growCord][initValue + 1] = 1;
        } else {
            this.mapMatrix[findRow(initValue)][initValue+1] =1;

        }
    }

    /**
     * Finds the rowNumber for the next point after currentColumn.
     */
    public int nextPointValue(int currentColum) {
        for (int c = currentColum + 1; c < colNumber; c++) {
            for (int r = 0; r < rowNumber; r++) {
                if (this.mapMatrix[r][c] == 1) {
                    return r;
                }
            }
        }
        return 0;
    }

    /**
     * Finds the rowNumber for a point in a column.
     */
    public int findRow(int colum) {
        for (int x = 0; x < rowNumber; x++) {
            if (this.mapMatrix[x][colum] == 1)
                return x;
        }
        return 0;
    }

    /**
     * Place the points which represents the ground texture in the model.
     */
    public void placeGround(){
        for(int c = 0; c < colNumber; c++){
            for(int r = rowNumber -1; r >= 0; r--){
                if(this.mapMatrix[r][c] == 1){
                    break;
                }
                mapMatrix[r][c] = 2;
            }
        }
    }

    /**
     *Sets the amount of platforms to be created on a map and the length of them
     */
    public void createPlatforms(int platforms, int length) {
        setPlatformStartPoints(platforms);
        for(int i = 0; i < platformStartPoints.length; i++) {
            int row = platformStartPoints[i][0];
            int startCol = platformStartPoints[i][1];
            for (int start = 0; start < length; start++) {
                mapMatrix[row][startCol] = 3;
                if(startCol < this.colNumber - 7) {
                    startCol++;
                }
            }
        }
    }

    /**
     *Sets the startpoints for each platform from which they may be expanded
     */
    public void setPlatformStartPoints(int startPoints) {
        int i = 0;
        for(int row = minPlatformRow; row <= maxPlatformRow; row++) {
            if(i < startPoints) {
                int c = random.nextInt(this.colNumber - 7);
                this.mapMatrix[row][c] = 3;
                platformStartPoints[i][0] = row;
                platformStartPoints[i][1] = c;
                i++;
            }
        }
    }

    /**
     *Sets the startpoints for each cloud from which they may be expanded
     */
    public void setCloudStartPoints(int startPoints) {
        int i = 0;
        for(int row = 2; row <= 6; row++) {
            if(i < startPoints) {
                int c = random.nextInt(this.colNumber -7);
                this.mapMatrix[row][c] = 3;
                cloudStartPoints[i][0] = row;
                cloudStartPoints[i][1] = c;
                i++;
            }
        }
    }
    /**
     *Sets the amount of clouds to be created on a map and the length of them
     */
    public void creatCloud(int clouds, int length) {
        setCloudStartPoints(clouds);
        for(int i = 0; i < cloudStartPoints.length; i++) {
            int row = cloudStartPoints[i][0];
            int startCol = cloudStartPoints[i][1];
            for (int start = 0; start < length; start++) {
                mapMatrix[row][startCol] = 4;
                if(startCol < this.colNumber - 7) {
                    startCol++;
                }
            }
        }
    }



    /**
     *Sets the amount of pitfalls to be created on a map and the length of them
     */
    public void createPitfalls(int pitfalls, int width) {
        setPitfallStartPoints(pitfalls);
        for(int i = 0; i < pitfallStartPoints.length; i++) {
            int startRow = pitfallStartPoints[i][0];
            int startCol = pitfallStartPoints[i][1];
            for(int length = 0; length < width; length++) {
                for(int depth = 0; depth < this.colNumber; depth++) {
                    this.mapMatrix[startRow][startCol] = 0;
                    if(startRow < this.rowNumber - 1) {
                        startRow++;
                    }
                }
                startRow = pitfallStartPoints[i][0];
                if(startCol < this.lastCol) {
                    startCol++;
                }
            }
        }
    }

    /**
     *Sets the startpoints for each pitfall from which they may be expanded
     */
    public void setPitfallStartPoints(int startPoints) {
        for(int row = minPitfallRow; row < startPoints; row++) {
                int c = random.nextInt(this.colNumber - 7);
                this.mapMatrix[mountainTop][c] = 0;
                pitfallStartPoints[row][0] = row;
                pitfallStartPoints[row][1] = c;
        }
    }

    /**
     *Setter
     */
    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    /**
     *Returns the same instance of the Generator every time it is called, or creates a new instance of Generator if none exists
     */
    public static Generator getGeneratorInstance() {
        if(generatorInstance == null) {
            generatorInstance = new Generator();
        }
        return generatorInstance;
    }

    /**
     *Sets the Generator instance to null, so a new generator instance can be created
     */
    public static void resetGeneratorInstance() {
        generatorInstance = null;
    }

    /**
     *Getter
     */
    public int getnRow() {
        return rowNumber;
    }

    /**
     *Getter
     */
    public int getnCol() {
        return colNumber;
    }

    /**
     *Getter
     */
    public int getContentAt(int x, int y) {
        return this.mapMatrix[y][x];
    }

}
