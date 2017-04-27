package test.com.mygdx.game.Dash;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Dash;
import com.mygdx.game.GameWorld;
import com.mygdx.game.Player;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static sun.audio.AudioPlayer.player;

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

/** 
* 
* Method: create() 
* 
*/ 
@Test
public void testCreate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: render() 
* 
*/ 
@Test
public void testRender() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: dispose() 
* 
*/ 
@Test
public void testDispose() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hej() 
* 
*/ 
@Test
public void testHej() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testPlayerHealth() {
        Dash dash = new Dash();
        GameWorld world = new GameWorld(dash);
        Player player = world.getPlayerCharacter();

        assertTrue(player.getHealth() == 3);
    }

}
