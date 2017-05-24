package com.mygdx.game.LibgdxWrapper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.Model.Generator;

/**
 * Created by Erik on 08/05/2017.
 */
public class LibgdxMap {

    private Generator arrayGenerator;
    private TiledMap map;

    private Texture texture;
    private Texture backgroundimg;

    private TiledMapTileLayer groundLayer;


    private TiledMapTile groundEdge;
    private TiledMapTile ground;
    private TiledMapTile sky;
    private TiledMapTile platform;

    private MapLayers layers;
    private int offsetX;

    private TextureRegion[][] splitTiles;

    public LibgdxMap() {
        offsetX = 0;

        arrayGenerator = Generator.getGeneratorInstance();


        texture = new Texture(Gdx.files.internal("tiles/Tiles_32x32.png"));
        splitTiles = TextureRegion.split(texture, 32, 32);

        groundLayer = new TiledMapTileLayer(arrayGenerator.getCol() + 500, arrayGenerator.getRow(), 32, 32);

        map = new TiledMap();
        layers = map.getLayers();

        groundEdge = new StaticTiledMapTile(splitTiles[0][0]);
        ground = new StaticTiledMapTile(splitTiles[0][4]);
        sky = new StaticTiledMapTile(splitTiles[5][7]);
        platform = new StaticTiledMapTile(splitTiles[0][6]);

        placeTexture();
        layers.add(groundLayer);

    }

    /**
     *place the next groundlayer based on a new generated model matrix.
     */
    public void setNextlibgdx_mapSegment() {
        offsetX += arrayGenerator.getCol();
        arrayGenerator.setNextMapStructure();
        addNewGroundlayer();
    }

    /**
     *place the next groundlayer at the start based on the current generated model matrix.
     */
    public void setLoopBacklibgdx_mapSegment() {
        offsetX = 0;
        addNewGroundlayer();
    }

    /**
     *place texture based on the map model matrix.
     */
    private void addNewGroundlayer() {
        if(layers.getCount() > 2) {
            layers.remove(0);
        }
        groundLayer = new TiledMapTileLayer(arrayGenerator.getCol() + offsetX, arrayGenerator.getRow(), 32, 32);
        placeTexture();
        layers.add(groundLayer);
    }

    /**
     *place texture based on the map model matrix.
     */
    private void placeTexture() {
        groundEdge.setId(1);
        ground.setId(0);
        platform.setId(3);

        for (int x = 0; x < arrayGenerator.getCol(); x++) {
            for (int y = arrayGenerator.getRow() - 1; y > 0; y--) {
                Cell cell = new Cell();
                int id = arrayGenerator.getMapArray(x, y);
                switch (id) {
                    case 0:
                        cell.setTile(sky);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getRow() - y, cell);
                        break;
                    case 1:
                        cell.setTile(groundEdge);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getRow() - y, cell);
                        break;
                    case 2:
                        cell.setTile(ground);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getRow() - y, cell);
                        break;
                    case 3:
                        cell.setTile(platform);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getRow() - y, cell);
                        break;
                }
            }
        }
    }

    /**
     *Getter
     */
    public TiledMap getMap() {
        return this.map;
    }

    /**
     *Getter
     */
    public int getMapModelCols() {
        return arrayGenerator.getCol();
    }

    /**
     *Getter
     */
    public int getMapModelRows() {
        return arrayGenerator.getRow();
    }

    /**
     *Getter
     */
    public int getArrayId(int x, int y) {
        return arrayGenerator.getMapArray(x, y);
    }

    /**
     *Getter
     */
    public int getOffsetX() {
        return offsetX;
    }

}
