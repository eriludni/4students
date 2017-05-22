package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Niklas on 2017-05-14.
 */
public class Hud {
    public Stage stage;
    public Viewport viewPort;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Integer health;

    private Label worldTimeLabel;
    private Label scoreCountLabel;
    private Label timeLabel;
    private Label healthCountLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label scoreLabel;
    private Label healthLabel;

    public Hud(){
        worldTimer = 0;
        timeCount = 0;
        score = 0;
        health = 0;

        viewPort = new FitViewport(CONSTANTS.WIDTH, CONSTANTS.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort);

        Table table = new  Table();
        table.top();
        table.setFillParent(true);

        healthCountLabel = new Label(String.format("%01d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldTimeLabel = new Label(String.format("%04d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreCountLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("DASH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        healthLabel.setFontScale(1.4f);
        healthCountLabel.setFontScale(1.4f);
        scoreLabel.setFontScale(1.4f);
        scoreCountLabel.setFontScale(1.4f);
        timeLabel.setFontScale(1.4f);
        worldTimeLabel.setFontScale(1.4f);

        table.add(healthLabel).expandX().padTop(10).spaceLeft(0);
        table.add(scoreLabel).expandX().padTop(10);
        //table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(healthCountLabel).expandX();
        table.add(scoreCountLabel).expandX();
        //table.add(levelLabel).expandX();
        table.add(worldTimeLabel).expandX();

        stage.addActor(table);
    }

    public void setScore(int score) {
        scoreCountLabel.setText(String.format("%06d", score));
    }

    public void setHealth(int health) {
        healthCountLabel.setText(String.format("%01d", health));
    }

    public void stepWorldTimer() {
        worldTimer++;
        worldTimeLabel.setText(String.format("%04d", worldTimer));
    }
}
