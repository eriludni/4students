package com.mygdx.game.libgdx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.Model.Generator;


/**
 * Created by Erik on 08/05/2017.
 */
public class libgdx_map {

    Generator arrayGenerator;
    TiledMap map;
    Texture texture;
    Texture backgroundimg;
    TiledMapTileLayer groundLayer;
    TiledMapImageLayer background;


    TiledMapTile groundEdge;
    TiledMapTile ground;


    public libgdx_map() {
        arrayGenerator = new Generator();
        texture = new Texture(Gdx.files.internal("tiles/Tiles_32x32.png"));
        backgroundimg = new Texture(Gdx.files.internal("tiles/BackgroundGradient.png"));
        groundLayer = new TiledMapTileLayer(arrayGenerator.getCol(), arrayGenerator.getRow(), 32, 32);
        background = new TiledMapImageLayer(new TextureRegion(backgroundimg), arrayGenerator.getCol(),arrayGenerator.getRow());
        TextureRegion[][] splitTiles = TextureRegion.split(texture, 32, 32);
        map = new TiledMap();
        MapLayers layers = map.getLayers();

        groundEdge = new StaticTiledMapTile(splitTiles[0][0]);
        ground = new StaticTiledMapTile(splitTiles[0][4]);


        for (int x = 0; x < arrayGenerator.getCol(); x++) {
            for (int y = arrayGenerator.getRow() - 1; y > 0; y--) {
                Cell cell = new Cell();
                int id = arrayGenerator.getMapArray(x, y);
                switch (id) {
                    case 0:
                        cell.setTile(new StaticTiledMapTile(splitTiles[5][0]));
                        groundLayer.setCell(x, arrayGenerator.getRow() - y, cell);
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
        layers.add(background)                                                                 
        layers.add(groundLayer);



    }

    public TiledMap getMap() {
        return this.map;
    }


}
