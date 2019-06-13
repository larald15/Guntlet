package main;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import controller.physics.PhysicsControler;
import controller.player.BetterFlyCam;
import controller.weapon.WeaponControler;
import de.lessvoid.nifty.Nifty;
import handler.actions.ActionHandler;
import handler.audio.AudioHandler;
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
    private WeaponControler weaponControler;
    private AudioHandler audioHandler;

    private BulletAppState bulletAppState = new BulletAppState();

    private Nifty nifty;
    public static boolean started = false;

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

        actionHandler = new ActionHandler(assetManager, cam, physicsControler,interfaceRenderer, rootNode);
        actionHandler.setUpKeys(inputManager);

        weaponControler = new WeaponControler(rootNode, actionHandler, movementHandler);

        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        nifty.loadControlFile("Interface/GUI/nifty-default-controls.xml");
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.fromXml("Interface/GUI/StartScreen.xml", "start");
        nifty.gotoScreen("start");
        
        audioHandler = new AudioHandler(assetManager);
        audioHandler.setupMusic();
    }

    @Override
    public void simpleUpdate(float tpf) {
        movementHandler.move(cam);
        if (!started) {
            flyCam.setEnabled(false);
            inputManager.setCursorVisible(true);
        } else {
            flyCam.setEnabled(true);
            inputManager.setCursorVisible(false);
            interfaceRenderer.showCrosshair(true);
            nifty.exit();
        }
        actionHandler.action(tpf);
        movementHandler.prevent360(cam);
        actionHandler.deleteBulletsAfterTime();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        interfaceRenderer.refreshHUD();
    }

}
