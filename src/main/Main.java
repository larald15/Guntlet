package main;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import controller.physics.PhysicsControler;
import handler.collision.CollisionHandler;
import handler.movement.MovementHandler;
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
    private PlayerRenderer playerRenderer;
    private CollisionHandler collisionHandler;

    private BulletAppState bulletAppState;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        movementHandler = new MovementHandler(rootNode, flyCam);
        mapRenderer = new MapRenderer(rootNode, assetManager, viewPort);
        playerRenderer = new PlayerRenderer(rootNode);
        collisionHandler = new CollisionHandler();

        collisionHandler.setUpCollision();

        playerRenderer.setUpPlayer(assetManager);

        mapRenderer.setUpLight();
        mapRenderer.setUpMap();

        movementHandler.setUpKeys(inputManager);
        movementHandler.setUpPlayer();

        physicsControler = new PhysicsControler(bulletAppState, movementHandler, mapRenderer);
        physicsControler.setUpPhysics(stateManager, rootNode, assetManager, viewPort, flyCam);
    }

    @Override
    public void simpleUpdate(float tpf) {
        movementHandler.move(cam, movementHandler.getPlayer());
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

}
