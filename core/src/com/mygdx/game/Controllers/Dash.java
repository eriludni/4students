package com.mygdx.game.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * @author
 * Responsibility: This is the class which sets up the rest of the game
 * Uses: IController, MenuController, GameOverController, PlayerController, PauseController
 * Used by: GameOverController, MenuController, PauseController, PlayerController
 */

public class Dash extends Game {

    private IController currentController;
    private MenuController mainMenuController;
    private GameOverController gameOverController;
    private PlayerController playerController;
    private PauseController pauseController;

    @Override
    public void create() {
        mainMenuController = new MenuController(this);
        playerController = new PlayerController(this);
        gameOverController = new GameOverController(this);
        pauseController = new PauseController(this);

        currentController = mainMenuController;

    }

    /**
     *
     * Getter
     */

    public MenuController getMainMenuController() {
        return mainMenuController;
    }
    /**
     *
     * Getter
     */

    public PlayerController getPlayerController() {
        return playerController;
    }
    /**
     *
     * Getter
     */

    public GameOverController getGameOverController()
    {    this.gameOverController = new GameOverController(this);
        return gameOverController;
    }
    /**
     *
     * Getter
     */

    public PauseController getPauseController() {
        return pauseController;
    }
    /**
     *
     * Getter for a new game
     */

    public PlayerController getNewPlayerController() {
        this.playerController = new PlayerController(this);
        return playerController;
    }

    /**
     *
     * Mainloop
     */
    @Override
    public void render() {

        super.render();
        this.currentController.handleInput(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }

    /**
     * Setter
     */
    public void setController(IController currentController) {
        this.currentController = currentController;
        this.currentController.setScreen();
    }
}
