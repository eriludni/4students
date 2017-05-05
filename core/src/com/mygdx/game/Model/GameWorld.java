package com.mygdx.game.Model;

import com.mygdx.game.libgdx.Dash;


/**
 * Created by Erik on 26/04/2017.
 */
public class GameWorld {

    private Dash game;
    private Player logicalPlayer;
    private Enemy logicalEnemy;

    public GameWorld(Player player, Enemy enemy, Dash game) {
        this.logicalPlayer = player;
        this.logicalEnemy = enemy;
        this.game = game;
    }

    /*
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
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdf.type = BodyDef.BodyType.StaticBody;
            bdf.position.set((rect.getX()+rect.getWidth() / 2)/Dash.PPM, (rect.getY()+ rect.getHeight() / 2)/Dash.PPM);

            body = world.createBody(bdf);

            shape.setAsBox((rect.getWidth() /2)/Dash.PPM, (rect.getHeight() /2)/Dash.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
   }
   */

   public Player getLogicalPlayerCharacter(){
       return logicalPlayer;
   }
   public Enemy getLogicalEnemyCharacter() {
       return logicalEnemy;
   }



}
