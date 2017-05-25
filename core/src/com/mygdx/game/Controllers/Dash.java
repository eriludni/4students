package com.mygdx.game.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

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

    public MenuController getMainMenuController() {
        return mainMenuController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public GameOverController getGameOverController() {
        return gameOverController;
    }

    public PauseController getPauseController() {
        return pauseController;
    }

    public PlayerController getNewPlayerController() {
        this.playerController = new PlayerController(this);
        return playerController;
    }

    @Override
    public void render() {

        super.render();
        this.currentController.handleInput(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }

    public void setController(IController currentController) {

        this.currentController = currentController;
        this.currentController.setScreen();
    }
}
