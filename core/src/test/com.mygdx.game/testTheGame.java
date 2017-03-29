package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Niklas on 2017-03-29.
 */
public class testTheGame {
    @Test
    public void thisAlwaysPasses()
    {
        assertTrue(true);
    }

    @Test
    public void testMyGdxGame()
    {
        theGame game = new theGame();
        game.hej();
    }

    // some random tests.
}
