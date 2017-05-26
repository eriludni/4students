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

    private TiledMapTileLayer groundLayer;


    private TiledMapTile groundEdge;
    private TiledMapTile ground;
    private TiledMapTile platform;
    private TiledMapTile cloudLeft;
    private TiledMapTile cloudRight;

    private boolean turnCloud = false;

    private MapLayers layers;
    private int offsetX;

    private TextureRegion[][] splitTiles;

    public LibgdxMap() {
        offsetX = 0;

        arrayGenerator = Generator.getGeneratorInstance();


        texture = new Texture(Gdx.files.internal("tiles/Tiles_32x32.png"));
        splitTiles = TextureRegion.split(texture, 32, 32);

        groundLayer = new TiledMapTileLayer(arrayGenerator.getnCol() + 500, arrayGenerator.getnRow(), 32, 32);

        map = new TiledMap();
        layers = map.getLayers();

        groundEdge = new StaticTiledMapTile(splitTiles[0][0]);
        ground = new StaticTiledMapTile(splitTiles[0][4]);
        platform = new StaticTiledMapTile(splitTiles[0][6]);
        cloudLeft = new StaticTiledMapTile(splitTiles[4][5]);
        cloudRight = new StaticTiledMapTile(splitTiles[4][6]);

        //placeTexture();
        //layers.add(groundLayer);

    }

    /**
     *place the next groundlayer based on a new generated model matrix.
     */
    public void setNextlibgdx_mapSegment() {
        offsetX += arrayGenerator.getnCol();
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
        groundLayer = new TiledMapTileLayer(arrayGenerator.getnCol() + offsetX, arrayGenerator.getnRow(), 32, 32);
        //placeTexture();
        //layers.add(groundLayer);
    }

    /**
     *place texture based on the map model matrix.
     */
    private void placeTexture() {
        groundEdge.setId(1);
        ground.setId(0);
        platform.setId(3);

        for (int x = 0; x < arrayGenerator.getnCol(); x++) {
            for (int y = arrayGenerator.getnRow() - 1; y > 0; y--) {
                Cell cell = new Cell();
                int id = arrayGenerator.getContentAt(x, y);
                switch (id) {
                    case 1:
                        cell.setTile(groundEdge);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getnRow() - y, cell);
                        break;
                    case 2:
                        cell.setTile(ground);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getnRow() - y, cell);
                        break;
                    case 3:
                        cell.setTile(platform);
                        groundLayer.setCell(x + offsetX, arrayGenerator.getnRow() - y, cell);
                        break;
                    case 4:
                        if(!turnCloud) {
                            cell.setTile(cloudLeft);
                            groundLayer.setCell(x + offsetX, arrayGenerator.getnRow() - y, cell);
                            turnCloud = true;
                        }else{
                            cell.setTile(cloudRight);
                            groundLayer.setCell(x + offsetX, arrayGenerator.getnRow() - y, cell);
                            turnCloud = false;
                        }
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
        return arrayGenerator.getnCol();
    }

    /**
     *Getter
     */
    public int getMapModelRows() {
        return arrayGenerator.getnRow();
    }

    /**
     *Getter
     */
    public int getArrayId(int x, int y) {
        return arrayGenerator.getContentAt(x, y);
    }

    /**
     *Getter
     */
    public int getOffsetX() {
        return offsetX;
    }

}
