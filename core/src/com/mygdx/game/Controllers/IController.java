package com.mygdx.game.Controllers;

/**
 * Created by Erik on 22/05/2017.
 *
 * @author Erik Lundin
 * Responsibility:
 * Uses:
 * Used by: Dash, GameOverController, MenuController, PauseController, PlayerController
 */
public interface IController {
     void handleInput(float dt);
     void setScreen();

}
