package com.mygdx.game.libgdx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Model.Generator;
import com.mygdx.game.Model.PowerUp;
import com.mygdx.game.Utils.CONSTANTS;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_world {
    private World world;

    private float currentPlayerXPos;

    private float triggerPos;

    private float xPositionOfFirstBody;

    private ArrayList<Libgdx_dynamic> dynamicalBodies = new ArrayList<Libgdx_dynamic>();

    private int maxSegmentCount = 3;

    private int segmentCounter = 0;

    private float counter = 0;

    private libgdx_player playerCharacter;

    private ArrayList<libgdx_enemy> enemyCharacters = new ArrayList<libgdx_enemy>();
    private ArrayList<libgdx_enemyBrain> EB = new ArrayList<libgdx_enemyBrain>();
    private ArrayList<libgdx_powerUp> lgdxPowerUps = new ArrayList<libgdx_powerUp>();

    private libgdx_map mapCreator;

    private static libgdx_world lgdxWorld;
    private GameWorld logicalWorld;

    private int xPositionOfLastBody = 0;

    private int furthestPositionReached = 0;

    private MyContactListener MCL;
    private Random rand = new Random();

    private TiledMap map;

    public libgdx_world(GameWorld logicalWorld) {
        this.world = new World(new Vector2(0, -10), true);
        this.logicalWorld = logicalWorld;
        mapCreator = new libgdx_map();
        this.map = mapCreator.getMap();
        createGroundHitbox(mapCreator, 0);
        triggerPos = getxPositionOfLastBody() - CONSTANTS.WIDTH / (2 * CONSTANTS.PPM);
        //logicalEnemies = logicalWorld.getLogicalEnemies();

        lgdxWorld = this;
        this.playerCharacter = new libgdx_player(logicalWorld.getLogicalPlayerCharacter());

        createLibgdxEnemies();
        createLibgdxPowerUps();

        MCL = new MyContactListener(world);
        this.world.setContactListener(MCL);
    }

    public void update(float dt){
        currentPlayerXPos = playerCharacter.getB2Body().getPosition().x;
        counter += dt*1.6;

        playerCharacter.update();
        world.step(1 / 60f, 6, 2);

        for(libgdx_enemy enemy: enemyCharacters){
            enemy.update(dt);
        }

        boolean hasReachedNormalTriggerPos = currentPlayerXPos > triggerPos && segmentCounter < maxSegmentCount;
        if (hasReachedNormalTriggerPos) {
            handleNewWorldSection();
            System.out.println("segmentCounter" + segmentCounter);
        }

        boolean hasReachedLoopTriggerPos = currentPlayerXPos > xPositionOfFirstBody + 6.2 && segmentCounter >= maxSegmentCount ;
        if(hasReachedLoopTriggerPos){
            System.out.println("goBack()");
            handleLoopBack();
        }

        if(playerCharacter.getModel().isDead()) {
            Generator.resetGeneratorInstance();
        }

        boolean hasBoldlyGoneWhereNoOneHasGoneBefore = (int)currentPlayerXPos > furthestPositionReached;
        if(hasBoldlyGoneWhereNoOneHasGoneBefore){
            updateHighscore();
        }

        removeStaticBodiesFromStartTo(counter);
        respawnEnemies();
        removePowerUp();
    }

    /**
     * Adds a new world section to the game.
     */
    private void handleNewWorldSection(){
        generateNewWorldSection();
        triggerPos = getxPositionOfLastBody() - CONSTANTS.WIDTH / (2 * CONSTANTS.PPM);
        respawnEverything();
        segmentCounter++;
    }

    /**
     * Generates the terrain of a new world section of the game.
     */

    private void generateNewWorldSection(){
        mapCreator.setNextlibgdx_mapSegment();
        int offsetX = mapCreator.getOffsetX();
        createGroundHitbox(mapCreator, offsetX);
    }


    /**
     * Creates hitboxes based on the map model provided by currentMap.
     */
    private void createGroundHitbox(libgdx_map currentMap, int offsetX){
        xPositionOfLastBody = (int)(((currentMap.getMapWidth() + offsetX) * 32 + 16) / CONSTANTS.PPM);
        if(segmentCounter == maxSegmentCount - 1) {
            xPositionOfFirstBody = (offsetX * 32 + 16) / CONSTANTS.PPM;
        }
        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (int x = 0; x < currentMap.getMapWidth(); x++) {
            for (int y = 0; y < currentMap.getMapHeight(); y++) {
                if (y == 0){
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / CONSTANTS.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / CONSTANTS.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / CONSTANTS.PPM, 16 / CONSTANTS.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                }
                if (currentMap.getArrayId(x, y) == 1 ||currentMap.getArrayId(x, y) == 3  ) {
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / CONSTANTS.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / CONSTANTS.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / CONSTANTS.PPM, 16 / CONSTANTS.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                    //body.setUserData("GroundEdge");
                    body.setUserData(this);
                }
            }
        }
    }

    /**
     * Puts the game back to the start position, thus completing the "loop".
     */
    private void handleLoopBack(){
        counter -= xPositionOfFirstBody;
        saveDynamicBodiesData();
        removeBodiesFromStartTo(getxPositionOfLastBody());
        generateLoopBackSection();
        createCloneBodies();
        triggerPos = getxPositionOfLastBody() - CONSTANTS.WIDTH / (2 * CONSTANTS.PPM);
        segmentCounter = 0;
        furthestPositionReached = (int) currentPlayerXPos;
    }

    /**
     * Saves the data of the dynamic bodies. Including vector, y-position and x-position relative to the beginning of the world section.
     */
    private void saveDynamicBodiesData(){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        Body body;
        int j = 0;
        for(int i = 0; i < bodies.size; i++){
            if(bodies.get(i).getType().getValue() == 2){
                body = bodies.get(i);
                System.out.println("(body.getPosition().x - xPositionOfFirstBody) * CONSTANTS.PPM: " + ((body.getPosition().x - xPositionOfFirstBody) * CONSTANTS.PPM));
                dynamicalBodies.add((Libgdx_dynamic) (body.getUserData()));
                dynamicalBodies.get(j).getModel().setxPos((body.getPosition().x - xPositionOfFirstBody) * CONSTANTS.PPM);
                dynamicalBodies.get(j).getModel().setyPos(body.getPosition().y * CONSTANTS.PPM);
                dynamicalBodies.get(j).getModel().setX_velocity(body.getLinearVelocity().x);
                dynamicalBodies.get(j).getModel().setY_velocity(body.getLinearVelocity().y);
                j++;
            }
        }
    }

    /**
     * Removes all the physical bodies from the start of the world to x.
     */
    private void removeBodiesFromStartTo(float x)
    {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(Body body: bodies){
            if(body.getPosition().x < x){
                world.destroyBody(body);
                body.setUserData(null);
            }
        }
    }

    /**
     * Creates a copy of the current world segment at the start of the world.
     */
    private void generateLoopBackSection(){
        mapCreator.setGoBacklibgdx_mapSegment();
        int offsetX = mapCreator.getOffsetX();
        createGroundHitbox(mapCreator, offsetX);
    }

    /**
     * Creates clone bodies of all saved dynamical bodies. These bodies have the same vector as the old bodies.
     */
    private void createCloneBodies(){
       // Libgdx_dynamic dynamicalBody;
       // for(int i = 0; i < dynamicalBodies.size(); i++){
       //     dynamicalBody = dynamicalBodies.get(i);
       //     System.out.println("dynamicalBody.getModel().getXPos(): " + dynamicalBody.getModel().getXPos());
       //     dynamicalBody.createBodyFromModel();
       // }
        for(Libgdx_dynamic dynamicalBody: dynamicalBodies){
            dynamicalBody.createBodyFromModel();
        }
        dynamicalBodies.clear();
    }

    /**
     * Updates the score with points for having progressed forward in the game.
     */
    private void updateHighscore(){
        int updatedHighscore = logicalWorld.getLogicalPlayerCharacter().getHighscore() + ((int)currentPlayerXPos - furthestPositionReached) * 10;
        logicalWorld.getLogicalPlayerCharacter().setHighscore(updatedHighscore);
        furthestPositionReached = (int)currentPlayerXPos;
    }

    /**
     * Removes all the static physical bodies from the start of the world to x. I.e. the bodies that make up the environment.
     */
    private void removeStaticBodiesFromStartTo(float x)
    {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(Body body: bodies){
            if(body.getPosition().x < x && body.getType().getValue() == 0){
                world.destroyBody(body);
                body.setUserData(null);
            }
        }

    }

    /**
     *Creates Libgdx enemies from the logical enemies
     */
    private void createLibgdxEnemies() {
        for(int i = 0; i < logicalWorld.getEnemyCount(); i++) {
            Enemy logicalEnemy = logicalWorld.getLogicalEnemies().get(i);
            enemyCharacters.add(new libgdx_enemy(logicalEnemy));
        }
    }

    /**
     *Creates Libgdx powerups from the logical powerups
     */
    private void createLibgdxPowerUps() {
        for(int i = 0; i < logicalWorld.getPowerUpCount(); i++) {
            PowerUp powerup = logicalWorld.getLogicalPowerUps().get(i);
            lgdxPowerUps.add(new libgdx_powerUp(powerup));
        }
    }

    /**
     *Removes all Libgdx enemies and creates a new array for libgdx enemies
     */
    private void removeAllLibgdxEnemies() {
        libgdx_enemy enemy;
        for(int i = 0; i < enemyCharacters.size(); i++) {
            enemy = enemyCharacters.get(i);
            enemyCharacters.remove(enemy);
            getWorld().destroyBody(enemy.getB2Body());
            //enemyCharacters.get(i).getModel().setDead(true);
        }
        enemyCharacters = new ArrayList<libgdx_enemy>();
    }

    /**
     *Removes all Libgdx powerups and creates a new array for libgdx powerups
     */
    private void removeAllLibgdxPowerUps() {
        for(int i = 0; i < lgdxPowerUps.size(); i++) {
            lgdxPowerUps.remove(i);
        }
        lgdxPowerUps = new ArrayList<libgdx_powerUp>();
    }

    //private void removeAllLibgdxEnemyBrains() {
    //    for(int i = 0; i < EB.size(); i++) {
    //        EB.remove(i);
    //    }
    //}
//
    //private void mapListManager(){
//
    //}

    /**
     *Removes all logical and libgdx enemies and powerups and recreates them at new positions
     */
    private void respawnEverything() {
        respawnAllEnemies();
        respawnAllPowerUps();
    }

    /**
     *Removes all libgdx and logical enemies and recreates them at new positions
     */
    private void respawnAllEnemies() {
        //lgdxWorld.removeAllLibgdxEnemyBrains();
        System.out.println("respawn_enemies");
        removeAllLibgdxEnemies();
        logicalWorld.removeAllLogicalPowerUps();
        logicalWorld.removeAllLogicalEnemyBrains();
        logicalWorld.removeAllLogicalEnemies();
        logicalWorld.createLogicalPowerUps();
        logicalWorld.createLogicalEnemies();
        logicalWorld.createLogicalEnemyBrains();
        createLibgdxEnemies();
        MCL.setLgdxEnemies(getEnemyCharacters());
        //lgdxWorld.createEnemyBrains();
    }

    /**
     *Removes all libgdx and logical powerups and recreates them at new positions
     */
    private void respawnAllPowerUps() {
        System.out.println("respawn_powerUps");
        removeAllLibgdxPowerUps();
        createLibgdxPowerUps();
    }

    /**
     *Removes a specific libgdx powerup that has been used
     */
    private void removePowerUp() {
        libgdx_powerUp powerUp;
        for(int i = 0; i < lgdxPowerUps.size(); i++) {
            if(lgdxPowerUps.get(i).getLogicalPowerUp().getToBeRemoved()) {
                powerUp = lgdxPowerUps.get(i);
                lgdxPowerUps.remove(powerUp);
                world.destroyBody(powerUp.getB2Body());
            }
        }
    }

    /**
     * Removes the bullets that has gone outside the screen.
     */

    public void removeBulletsOutSideScreen(float gameCamPositionX, float gameCamPositionY, float screenWidth, float screenHeight) {//world
        Array<Body> bodies = new Array<Body>(10);
        world.getBodies(bodies);
        for (int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            if (body != null && body.isBullet()) {
                //libgdx_body_userdata data = (libgdx_body_userdata) body.getUserData();
                libgdx_projectile data = (libgdx_projectile) body.getUserData();
                boolean bulletOutOfBounds = body.getPosition().x < gameCamPositionX - screenWidth / (2 * CONSTANTS.PPM) ||
                        body.getPosition().x > gameCamPositionX + screenWidth / (2 * CONSTANTS.PPM) ||
                        body.getPosition().y < 0 ||
                        body.getPosition().y > gameCamPositionY + screenHeight / (2 * CONSTANTS.PPM);

                if (data.isSetForRemoval() || bulletOutOfBounds) {
                    world.destroyBody(body);
                    body.setUserData(null);
                }
            }
        }
    }

    /**
     *Removes an enemy that is dead and respawns it at a new position.
     */
    private void respawnEnemies() {//world
        ArrayList<libgdx_enemy> enemies;
        enemies = getEnemyCharacters();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getModel().isDead()) {
                libgdx_enemy enemy = enemies.get(i);
                enemies.remove(enemy);
                world.destroyBody(enemy.getB2Body());
                logicalWorld.getLogicalPlayerCharacter().setHighscore(
                        logicalWorld.getLogicalPlayerCharacter().getHighscore() + 100);

                int distance = (rand.nextInt(7) + 5) * 100;

                enemies.add(new libgdx_enemy(new Enemy(3, 0.1f, 0,
                        logicalWorld.getLogicalPlayerCharacter().getXPos() * CONSTANTS.PPM + distance,
                        logicalWorld.getLogicalPlayerCharacter().getYPos() * CONSTANTS.PPM + 50,
                        10)));
            }
        }
    }

    /**
     *Getter
     */
    public int getxPositionOfLastBody() {
        return xPositionOfLastBody;
    }

    /**
     *Getter
     */
    static libgdx_world getlgdxWorld() {
        return lgdxWorld;
    }

    /**
     *Getter
     */
    public GameWorld getLogicalWorld() {
        return logicalWorld;
    }

    /**
     *Getter
     */
    public libgdx_player getPlayerCharacter() {
        return playerCharacter;
    }

    /**
     *Getter
     */
    public ArrayList<libgdx_enemy> getEnemyCharacters() {
        return enemyCharacters;
    }

    /**
     *Getter
     */
    public World getWorld() {
        return world;
    }

    /**
     *Getter
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     *Getter
     */
    public ArrayList<libgdx_enemyBrain> getEB() {
        return EB;
    }
}
