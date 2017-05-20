package com.mygdx.game.Screens.MenuSkins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Dash;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverSkins {
    Skin skin;

    public GameOverSkins() {

        /**
         * MainMenu font
         */
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        /**
         * MainMenu Textures
         */
        Pixmap pixmap = new Pixmap( CONSTANTS.WIDTH/ 4, CONSTANTS.HEIGHT / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        /**
         * MainMenu ButtonStyle
         */
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    public Skin getSkins(){
        return skin;
    }
}
