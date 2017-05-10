package com.mygdx.game.libgdx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Model.Generator;


/**
 * Created by Erik on 08/05/2017.
 */
public class libgdx_map {

   private Generator arrayGenerator;
   private TiledMap map;

   private Texture texture;
   private Texture backgroundimg;

   private TiledMapTileLayer groundLayer;
   private TiledMapImageLayer background;

   private TiledMapTile groundEdge;
   private TiledMapTile ground;

    public libgdx_map() {
        arrayGenerator = new Generator();
        texture = new Texture(Gdx.files.internal("tiles/Tiles_32x32.png"));
        backgroundimg = new Texture(Gdx.files.internal("tiles/BackgroundGradient.png"));
        TextureRegion[][] splitTiles = TextureRegion.split(texture, 32, 32);

        groundLayer = new TiledMapTileLayer(arrayGenerator.getCol(), arrayGenerator.getRow(), 32, 32);
        background = new TiledMapImageLayer(new TextureRegion(backgroundimg), arrayGenerator.getCol(),arrayGenerator.getRow());

        map = new TiledMap();
        MapLayers layers = map.getLayers();

        groundEdge = new StaticTiledMapTile(splitTiles[0][0]);
        ground = new StaticTiledMapTile(splitTiles[0][4]);

        groundEdge.setId(1);
        ground.setId(0);

        for (int x = 0; x < arrayGenerator.getCol(); x++) {
            for (int y = arrayGenerator.getRow() - 1; y > 0; y--) {
                Cell cell = new Cell();
                int id = arrayGenerator.getMapArray(x, y);
                switch (id) {
                    case 0:
                        break;
                    case 1:
                        cell.setTile(groundEdge);
                        groundLayer.setCell(x, arrayGenerator.getRow() - y, cell);
                        break;
                    case 2:
                        cell.setTile(ground);
                        groundLayer.setCell(x, arrayGenerator.getRow() - y, cell);
                        break;
                }
            }
        }
        layers.add(background);
        layers.add(groundLayer);


    }

    public TiledMap getMap() {
        return this.map;
    }

    public int getMapWidth(){
        return arrayGenerator.getCol();
    }
    public int getMapHeight(){
        return arrayGenerator.getRow();
    }
    public int getArrayId(int x, int y){
        return arrayGenerator.getMapArray(x,y);
    }

}
