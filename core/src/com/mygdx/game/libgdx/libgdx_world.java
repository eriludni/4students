package com.mygdx.game.libgdx;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.Player;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_world {
    private Dash game;
    private World world;
    private libgdx_player playerCharacter;
    private libgdx_enemy enemyCharacter;
    private Player logicalPlayer = new Player(3, 2, 0, 100, 100, 5);
    private Enemy logicalEnemy = new Enemy(3, 2, 0, 200, 100, 10);
    private static libgdx_world lgdxWorld;

    private TmxMapLoader maploader;
    private TiledMap map;

    public libgdx_world(Dash game) {
            this.game = game;
            world = new World(new Vector2(0, -10), true);
            libgdx_map mapCreator = new libgdx_map();

            //maploader = new TmxMapLoader();
            map = mapCreator.getMap(); //maploader.load("map.tmx");

            BodyDef bdf = new BodyDef();
            PolygonShape shape = new PolygonShape();
            FixtureDef fdef = new FixtureDef();
            Body body;

            for (MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                bdf.type = BodyDef.BodyType.StaticBody;
                bdf.position.set((rect.getX() + rect.getWidth() / 2) / Dash.PPM, (rect.getY() + rect.getHeight() / 2) / Dash.PPM);

                body = world.createBody(bdf);

                shape.setAsBox((rect.getWidth() / 2) / Dash.PPM, (rect.getHeight() / 2) / Dash.PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
           /* for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                bdf.type = BodyDef.BodyType.StaticBody;
                bdf.position.set((rect.getX() + rect.getWidth() / 2) / Dash.PPM, (rect.getY() + rect.getHeight() / 2) / Dash.PPM);

                body = world.createBody(bdf);

                shape.setAsBox((rect.getWidth() / 2) / Dash.PPM, (rect.getHeight() / 2) / Dash.PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
            */

        lgdxWorld = this;
            playerCharacter = new libgdx_player(logicalPlayer);
            enemyCharacter = new libgdx_enemy(logicalEnemy);
    }

    public static libgdx_world getlgdxWorld() {
        return lgdxWorld;
    }

    public libgdx_player getPlayerCharacter() {
        return playerCharacter;
    }

    public libgdx_enemy getEnemyCharacter() {
        return enemyCharacter;
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}
