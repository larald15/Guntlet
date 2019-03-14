package main;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import controller.physics.PhysicsControler;
import controller.player.BetterFlyCam;
import handler.actions.ActionHandler;
import handler.collision.CollisionHandler;
import handler.movement.MovementHandler;
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

    private BulletAppState bulletAppState = new BulletAppState();

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setZoomSpeed(0);
        flyCam = new BetterFlyCam(cam);

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
        playerRenderer.setUpGirl(assetManager);

        interfaceRenderer = new InterfaceRenderer(rootNode, assetManager, guiFont, settings, guiNode);
        interfaceRenderer.renderCrosshair();

        actionHandler = new ActionHandler(assetManager, cam, bulletAppState, rootNode);
        actionHandler.setUpKeys(inputManager);
    }

    @Override
    public void simpleUpdate(float tpf) {
        movementHandler.move(cam);
        actionHandler.action();
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

}
