package test.com.mygdx.game.Dash;

import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Model.Generator;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.assertTrue;

/** 
* Game Tester.
* 
* @author <Authors name> 
* @since <pre>mar 29, 2017</pre> 
* @version 1.0 
*/ 
public class dashTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testPlayerReduceHealth() {
        Player player = new Player(3, 2, 0, 100, 100, 5);
        player.reduceHealth(1);
        int health = player.getHealth();
        assertTrue(health == 2);
    }

    @Test
    public void testReversePlayerXVelocity() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        float xVelocity = player.getX_velocity();
        player.reverseXVelocity();
        assertTrue(player.getX_velocity() == -xVelocity);
    }

    @Test
    public void testReversePlayerYVelocity() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        float yVelocity = player.getY_velocity();
        player.reverseYVelocity();
        assertTrue(player.getY_velocity() == -yVelocity);
    }

    @Test
    public void testPlayerCheckDead() {
        Player player = new Player(3, 2, 2, 100, 100, 5);
        player.setDead(true);
        player.checkDead();
        assertTrue(player.getToBeRemoved() == true);
    }

    @Test
    public void testEnemyReduceHealth() {
        Enemy enemy = new Enemy(3, 2, 0, 100, 100, 5);
        enemy.reduceHealth(1);
        int health = enemy.getHealth();
        assertTrue(health == 2);
    }

    @Test
    public void testReverseEnemyXVelocity() {
        Enemy enemy = new Enemy(3, 2, 2, 100, 100, 5);
        float xVelocity = enemy.getX_velocity();
        enemy.reverseXVelocity();
        assertTrue(enemy.getX_velocity() == -xVelocity);
    }

    @Test
    public void testReverseEnemyYVelocity() {
        Enemy enemy = new Enemy(3, 2, 2, 100, 100, 5);
        float yVelocity = enemy.getY_velocity();
        enemy.reverseYVelocity();
        assertTrue(enemy.getY_velocity() == -yVelocity);
    }

    @Test
    public void testEnemyCheckDead() {
        Enemy enemy = new Enemy(3, 2, 2, 100, 100, 5);
        enemy.setDead(true);
        enemy.checkDead();
        assertTrue(enemy.getToBeRemoved() == true);
    }

    @Test
    public void testGeneratorSetNextMapStructure() {
        Generator generator = new Generator();
        int lastPoint = generator.findRow(generator.getCol() - 1);
        generator.setNextMapStructure();
        int firstPoint = generator.findRow(0);
        assertTrue(lastPoint == firstPoint);
    }

    @Test
    public void testGeneratorClear() {
        Generator generator = new Generator();
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

    @Test
    public void testGeneratorSetBasePointsFrom() {
        Generator generator = new Generator();
        int x = 5;
        generator.setBasePointsFrom(x);
        assertTrue(generator.getMapArray(0, x) == 1);
    }

    @Test
    public void testGeneratorNextPointValue() {
        Generator generator = new Generator();
        int x = 5;
        int firstPoint = generator.findRow(x);
        int secondPoint = generator.nextPointValue(x - 1);
        assertTrue(firstPoint == secondPoint);
    }

    @Test
    public void testGeneratorFindRow() {
        Generator generator = new Generator();
        int x = 0;
        int value = 0;
        int value2 = generator.findRow(x);
        int[][] array = new int[generator.getRow()][generator.getCol()];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                array[i][j] = generator.getMapArray(j, i);
            }
        }
        for(int i = 0; i < array.length; i++) {
            for(int j = x; j <= x; j++) {
                if(array[i][j] == 1) {
                    value = i;
                }
            }
        }
        assertTrue(value == value2);
    }
}
