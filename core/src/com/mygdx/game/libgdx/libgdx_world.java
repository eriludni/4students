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
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Model.Player;
import com.badlogic.gdx.physics.box2d.ContactListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_world {
    private Dash game;
    private World world;

    private libgdx_player playerCharacter;

    private ArrayList<Enemy> logicalEnemies;
    private ArrayList<libgdx_enemy> enemyCharacters = new ArrayList<libgdx_enemy>();

    private libgdx_map mapCreator;
    private ArrayList<libgdx_map> mapList;

    private static libgdx_world lgdxWorld;
    private GameWorld logicalWorld;

    private TiledMap map;

    public libgdx_world(Dash game, GameWorld logicalWorld) {

        this.game = game;
        this.world = new World(new Vector2(0, -10), true);
        this.logicalWorld = logicalWorld;
        //this.mapCreator = new libgdx_map();
        mapList = new ArrayList<libgdx_map>();
        mapList.add(new libgdx_map());
        this.map = mapList.get(0).getMap();
        createGroundHitbox(mapList.get(0), 0);

        logicalEnemies = logicalWorld.getLogicalEnemies();

        this.lgdxWorld = this;
        this.playerCharacter = new libgdx_player(logicalWorld.getLogicalPlayerCharacter());

        createLibgdxEnemies();

        this.world.setContactListener(new MyContactListener(world));
    }

    public void createLibgdxEnemies() {
        for(int i = 0; i < logicalEnemies.size(); i++) {
            Enemy logicalEnemy = logicalWorld.getLogicalEnemies().get(i);
            enemyCharacters.add(new libgdx_enemy(logicalEnemy));
        }
    }

    private void createGroundHitbox(libgdx_map currentMap, int offsetX){
        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (int x = 0; x < currentMap.getMapWidth(); x++) {
            for (int y = 0; y < currentMap.getMapHeight(); y++) {
                if (y == 0){
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / Dash.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / Dash.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / Dash.PPM, 16 / Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                }
                if (currentMap.getArrayId(x, y) == 1) {
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / Dash.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / Dash.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / Dash.PPM, 16 / Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                    //body.setUserData("GroundEdge");
                    body.setUserData(this);
                }
            }
        }
    }

    public void removeAllLibgdxEnemies() {
        for(int i = 0; i < enemyCharacters.size(); i++) {
            enemyCharacters.remove(i);
        }
    }

    public void mapListManager(){

    }

    public void updateWorld(){
        mapList.get(0).setNextlibgdx_map();
        int offsetX = mapList.get(0).getOffsetX();
        createGroundHitbox(mapList.get(0), offsetX);
    }

    public static libgdx_world getlgdxWorld() {
        return lgdxWorld;
    }

    public GameWorld getLogicalWorld() {
        return logicalWorld;
    }

    public libgdx_player getPlayerCharacter() {
        return playerCharacter;
    }

    public ArrayList<libgdx_enemy> getEnemyCharacters() {
        return enemyCharacters;
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}
