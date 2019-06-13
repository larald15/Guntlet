package main;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import controller.physics.PhysicsControler;
import controller.player.BetterFlyCam;
import controller.weapon.WeaponControler;
import handler.actions.ActionHandler;
import handler.collision.CollisionHandler;
import handler.enemies.EnemyHandler;
import handler.movement.MovementHandler;
import handler.sounds.BackgroundMusic;
import renderer.interfaces.InterfaceRenderer;
import renderer.map.MapRenderer;
import renderer.player.PlayerRenderer;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private MovementHandler movementHandler;
    private MapRenderer mapRenderer;
    private PhysicsControler physicsControler;
    private CollisionHandler collisionHandler;
    private PlayerRenderer playerRenderer;
    private InterfaceRenderer interfaceRenderer;
    private ActionHandler actionHandler;
    private WeaponControler weaponControler;
    private EnemyHandler enemyHandler;
    private BackgroundMusic backgroundMusic;

    private BulletAppState bulletAppState = new BulletAppState();

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setZoomSpeed(0);
        flyCam = new BetterFlyCam(cam);

        setDisplayFps(false);
        setDisplayStatView(false);

        mapRenderer = new MapRenderer(rootNode, assetManager, viewPort);
        collisionHandler = new CollisionHandler();
        movementHandler = new MovementHandler(collisionHandler, rootNode, assetManager, cam);

        collisionHandler.setUpCollision();

        mapRenderer.setUpLight();
        mapRenderer.setUpMap();

        movementHandler.setUpKeys(inputManager);
        movementHandler.setUpPlayer(flyCam);

        physicsControler = new PhysicsControler(bulletAppState, movementHandler, mapRenderer);
        physicsControler.setUpPhysics(stateManager, rootNode, assetManager, viewPort, flyCam);

        playerRenderer = new PlayerRenderer(rootNode);

        interfaceRenderer = new InterfaceRenderer(rootNode, assetManager, settings, guiNode);
        interfaceRenderer.renderCrosshair();

        actionHandler = new ActionHandler(assetManager, cam, physicsControler, rootNode);
        actionHandler.setUpKeys(inputManager);

        weaponControler = new WeaponControler(rootNode, actionHandler, movementHandler);
        
        //music
        backgroundMusic = new BackgroundMusic(assetManager);
        backgroundMusic.setUpHardBass();
        
        //enemies
        enemyHandler = new EnemyHandler(rootNode, assetManager);
        enemyHandler.spawnEnemies();
    }

    @Override
    public void simpleUpdate(float tpf) {
        movementHandler.move(cam);
        actionHandler.action(tpf);
        movementHandler.prevent360(cam);

        actionHandler.deleteBulletsAfterTime();
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        interfaceRenderer.refreshHUD();
    }

}
