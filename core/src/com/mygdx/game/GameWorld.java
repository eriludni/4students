package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {

    private Dash game;
    private World world;
    private Player playerCharacter;
    private Enemy enemyCharacter;

    private TmxMapLoader maploader;
    private TiledMap map;



    public GameWorld(Dash game){
        this.game = game;
        world = new World(new Vector2(0,-10),true);

        maploader  = new TmxMapLoader();
        map = maploader.load("map.tmx");

        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdf.type = BodyDef.BodyType.StaticBody;
            bdf.position.set((rect.getX()+rect.getWidth() / 2)/Dash.PPM, (rect.getY()+ rect.getHeight() / 2)/Dash.PPM);

            body = world.createBody(bdf);

            shape.setAsBox((rect.getWidth() /2)/Dash.PPM, (rect.getHeight() /2)/Dash.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        playerCharacter = new Player(this);
        enemyCharacter = new Enemy(3, 10, 10, 200, 200, 10, this);

   }

   public World getWorld(){
        return world;
   }
   public TiledMap getMap(){
       return map;
   }
   public Player getPlayerCharacter(){
       return playerCharacter;
   }



}
