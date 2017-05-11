package com.mygdx.game.libgdx;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.Player;
import com.badlogic.gdx.physics.box2d.ContactListener;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_world {
    private Dash game;
    private World world;
    private libgdx_player playerCharacter;
    private libgdx_enemy enemyCharacter;
    private Player logicalPlayer = new Player(3, 0.1f, 0, 100, 300, 10);
    private Enemy logicalEnemy = new Enemy(3, 0.1f, 0, 200, 300, 20);
    private static libgdx_world lgdxWorld;

    private TiledMapTileLayer objectLayer;

    private TmxMapLoader maploader;
    private TiledMap map;

    public libgdx_world(Dash game) {
//<<<<<<< Updated upstream
        this.game = game;
        world = new World(new Vector2(0, -10), true);

        libgdx_map mapCreator = new libgdx_map();

//=======
//            this.game = game;
//            world = new World(new Vector2(0, -10), true);
//            world.setContactListener(new MyContactListener(world));
//            maploader = new TmxMapLoader();
//            map = maploader.load("map.tmx");
//>>>>>>> Stashed changes

        map = mapCreator.getMap();


        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for (int x = 0; x < mapCreator.getMapWidth(); x++) {
            for (int y = 0; y < mapCreator.getMapHeight(); y++) {
                if (mapCreator.getArrayId(x, y) == 1) {
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set((x*32+16)/Dash.PPM ,((mapCreator.getMapHeight() - y)*32+16)/Dash.PPM );

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / Dash.PPM, 16 / Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                    body.setUserData("GroundEdge");
                    System.out.println(body.getUserData());
                }
            }
//<<<<<<< Updated upstream
        }

//=======
//>>>>>>> Stashed changes
        lgdxWorld = this;
        playerCharacter = new libgdx_player(logicalPlayer);
        enemyCharacter = new libgdx_enemy(logicalEnemy);

        world.setContactListener(new MyContactListener(world));
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
