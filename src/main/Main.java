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
        mapRenderer = new MapRenderer(rootNode, assetManager, viewPort);
        collisionHandler = new CollisionHandler();
        movementHandler = new MovementHandler(collisionHandler);

        collisionHandler.setUpCollision();

        mapRenderer.setUpLight();
        mapRenderer.setUpMap();

        movementHandler.setUpKeys(inputManager);
        movementHandler.setUpPlayer(flyCam);

        physicsControler = new PhysicsControler(bulletAppState, movementHandler, mapRenderer);
        physicsControler.setUpPhysics(stateManager, rootNode, assetManager, viewPort, flyCam);
    }

    @Override
    public void simpleUpdate(float tpf) {
        movementHandler.move(cam);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }

}
