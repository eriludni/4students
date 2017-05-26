package com.mygdx.game;

import com.mygdx.game.Model.*;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.awt.*;

import static java.lang.Math.*; 
import static org.junit.Assert.assertTrue;

/** 
* Game Tester.
* 
* @author <Authors name> 
* @since <pre>mar 29, 2017</pre> 
* @version 1.0 
*/ 
public class DashTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Tests if the player health is reduced correctly
     */
    @Test
    public void testPlayerReduceHealth() {
        Player player = new Player(3, 2, 0, 100, 100, 5);
        player.reduceHealth(1);
        int health = player.getHealth();
        assertTrue(health == 2);
    }

    /**
     * Tests if the player x velocity is reversed correctly
     */
    @Test
    public void testReversePlayerXVelocity() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        float xVelocity = player.getX_velocity();
        player.reverseXVelocity();
        assertTrue(player.getX_velocity() == -xVelocity);
    }

    /**
     * Tests if the player y velocity is reversed correctly
     */
    @Test
    public void testReversePlayerYVelocity() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        float yVelocity = player.getY_velocity();
        player.reverseYVelocity();
        assertTrue(player.getY_velocity() == -yVelocity);
    }

    /**
     * Tests if the player is dead when its health is less than or equal to 0
     */
    @Test
    public void testPlayerCheckDead() {
        Player player = new Player(0, 2, 2, 100, 100, 5);
        player.checkDead();
        assertTrue(player.isDead() == true);
    }

    /**
     * Tests if when the player crosses a certain x position, enemies are set to respawn and that the x position to be crossed is moved forward
     */
    @Test
    public void testPlayerCheckXSpawnPosCrossed() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        int x = 50;
        float xSpawnPos = player.getxSpawnPos();
        player.setxPos(x);
        player.checkxSpawnPosCrossed();
        assertTrue((player.getRespawnEnemies()) && (player.getxSpawnPos() == (xSpawnPos + 20)));
    }

    /**
     * Tests if the enemy health is reduced correctly
     */
    @Test
    public void testEnemyReduceHealth() {
        Enemy enemy = new Enemy(3, 2, 0, 100, 100, 5);
        enemy.reduceHealth(1);
        int health = enemy.getHealth();
        assertTrue(health == 2);
    }

    /**
     * Tests if the enemy x velocity is reversed correctly
     */
    @Test
    public void testReverseEnemyXVelocity() {
        Enemy enemy = new Enemy(3, 2, 2, 100, 100, 5);
        float xVelocity = enemy.getX_velocity();
        enemy.reverseXVelocity();
        assertTrue(enemy.getX_velocity() == -xVelocity);
    }

    /**
     * Tests if the enemy y velocity is reversed correctly
     */
    @Test
    public void testReverseEnemyYVelocity() {
        Enemy enemy = new Enemy(3, 2, 2, 100, 100, 5);
        float yVelocity = enemy.getY_velocity();
        enemy.reverseYVelocity();
        assertTrue(enemy.getY_velocity() == -yVelocity);
    }

    /**
     * Tests if the enemy is dead when its health is less than or equal to 0
     */
    @Test
    public void testEnemyCheckDead() {
        Enemy enemy = new Enemy(0, 2, 2, 100, 100, 5);
        enemy.checkDead();
        assertTrue(enemy.isDead() == true);
    }

    /**
     * Tests if the array in the Generator can be cleared by setting all of the cells in the array to 0
     */
    @Test
    public void testGeneratorClear() {
        Generator generator = Generator.getGeneratorInstance();
        int[][] array = new int[generator.getRow()][generator.getCol()];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                array[i][j] = 1;
            }
        }
        generator.clear(array);
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                assertTrue(array[i][j] == 0);
            }
        }
    }

    /**
     * Tests if the Generator sets the first basepoint in a new array to 1 at the specified column
     */
    @Test
    public void testGeneratorSetBasePointsFrom() {
        Generator generator = Generator.getGeneratorInstance();
        int x = 5;
        generator.setBasePointsFrom(x);
        assertTrue(generator.getMapArray(0, x) == 1);
    }

    /**
     * Tests if the nextPointValue() function finds the correct value
     */
    @Test
    public void testGeneratorNextPointValue() {
        Generator generator = Generator.getGeneratorInstance();
        int x = 5;
        int firstPoint = generator.findRow(x);
        int secondPoint = generator.nextPointValue(x - 1);
        assertTrue(firstPoint == secondPoint);
    }

    @Test
    public void testGetLaunchPosition(){
        Point characterPosition = new Point(1,3);
        Point targetPosition = new Point(4, 6);
        float hitBoxRadius = 5f;
        Point launchPoint = Projectile.getLaunchPosition(characterPosition,targetPosition,hitBoxRadius);

        double deltaXlaunchPoint = launchPoint.x - characterPosition.x;
        double deltaYlaunchPoint = launchPoint.y - characterPosition.y;

        double deltaXtargetPosition = targetPosition.x - characterPosition.x;
        double deltaYtargetPosition = targetPosition.y - characterPosition.y;

        double angleLaunchPoint = atan(deltaYlaunchPoint / deltaXlaunchPoint);
        double angleTargetPosition = atan(deltaYtargetPosition / deltaXtargetPosition);

        double angleDiff = angleLaunchPoint - angleTargetPosition;

        double hypothenuse = sqrt(pow(deltaYlaunchPoint, 2) + pow(deltaXlaunchPoint, 2));
        double hypothenuseDiff = hypothenuse - hitBoxRadius;

        assertTrue(abs(angleDiff) < 1 && abs(hypothenuseDiff) < 1);
    }

    /**
     * Tests if all of the logical enemies are removed correctly
     */
    @Test
    public void testGameWorldRemoveAllLogicalEnemies() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalEnemies();
        assertTrue(world.getLogicalEnemies().size() == 0);
    }

    /**
     * Tests if all of the logical enemy brains are removed correctly
     */
    @Test
    public void testGameWorldRemoveAllLogicalEnemyBrains() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalEnemyBrains();
        assertTrue(world.getLogicalEnemyBrains().size() == 0);
    }

    /**
     * Tests if all of the logical powerups are removed correctly
     */
    @Test
    public void testGameWorldRemoveAllLogicalPowerUps() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalPowerUps();
        assertTrue(world.getLogicalPowerUps().size() == 0);
    }

    /**
     * Tests if the logical enemies are created correctly
     */
    @Test
    public void testGameWorldCreateLogicalEnemies() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalEnemies();
        world.createLogicalEnemies();
        assertTrue(world.getLogicalEnemies().size() == world.getEnemyCount());
    }

    /**
     * Tests if the logical enemy brains are created correctly
     */
    @Test
    public void testGameWorldCreateLogicalEnemyBrains() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalEnemyBrains();
        world.createLogicalEnemyBrains();
        assertTrue(world.getLogicalEnemyBrains().size() == world.getEnemyCount());
    }

    /**
     * Tests if the logical powerups are created correctly
     */
    @Test
    public void testGameWorldCreateLogicalPowerUps() {
        GameWorld world = new GameWorld();
        world.removeAllLogicalPowerUps();
        world.createLogicalPowerUps();
        assertTrue(world.getLogicalPowerUps().size() == world.getPowerUpCount());
    }

    /**
     * Tests if a projectile collides correctly with objects
     */
    @Test
    public void testProjectileJustCollided() {
        Projectile projectile = new Projectile(5, 5);
        projectile.JustCollided();
        assertTrue(projectile.hasCollided());
    }

    /**
     * Tests the Use Case The player moves takes damage and dies
     */
    @Test
    public void testUCPlayerMovesTakesDamageAndDies() {
        int startHealth = 3;
        float startXPos = 2;
        int dmg = 3;
        Player player = new Player(startHealth, startXPos, 2, 100, 200, 5);
        player.setxPos(startXPos + 2);
        player.reduceHealth(dmg);
        player.checkDead();
        assertTrue(player.getXPos() > startXPos && player.getHealth() == startHealth - dmg && player.isDead());
    }

    /**
     * Tests the Use Case The player moves takes damage and survives
     */
    @Test
    public void testUCPlayerMovesTakesDamageAndSurvives(){
        int startHealth = 3;
        float startXPos = 2;
        float startYPos = 2;
        int dmg = 1;
        Player player = new Player(startHealth, startXPos, startYPos, 100, 200, 5);
        player.setxPos(startXPos + 5);
        player.reduceHealth(dmg);
        player.checkDead();
        assertTrue(player.getXPos() > startXPos && player.getHealth() == startHealth - dmg && !player.isDead());
    }
}
