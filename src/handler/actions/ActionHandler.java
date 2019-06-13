/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.actions;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import controller.physics.PhysicsControler;
import controller.weapon.WeaponControler;
import data.PlayerData;
import handler.movement.KeyListener;
import handler.movement.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import renderer.bullet.BulletRenderer;
import renderer.interfaces.InterfaceRenderer;

/**
 *
 * @author Larsen
 */
public class ActionHandler {

    private BulletRenderer bulletRenderer;
    private AssetManager assetManager;
    private Camera cam;
    private Node rootNode;

    private Node bulletNodes = new Node();

    private PhysicsControler physicsControler;
    private WeaponControler weaponControler;
    private InterfaceRenderer interfaceRenderer;

    private final float fireRate = 0.15f;
    private float fireTimer = fireRate;

    private boolean reloadPause = false;
    private boolean bulletReadyToDelete = false;

    //Mouse Triggers
    public static final Trigger Trigger_LEFT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    public static final Trigger Trigger_RIGHT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);

    //Mouse Mappings
    public static final String MAPPING_LEFT_CLICK = "Left Click";
    public static final String MAPPING_RIGHT_CLICK = "Right Click";

    //Key Triggers
    public static final Trigger Trigger_RELOAD = new KeyTrigger(KeyInput.KEY_R);
    public static final Trigger Trigger_MENU = new KeyTrigger(KeyInput.KEY_I);

    //Key Mappings
    public static final String MAPPING_RELOAD = "Reload";
    public static final String MAPPING_MENU = "Menu";

    public ActionHandler(AssetManager assetManager, Camera cam, PhysicsControler physicsControler, InterfaceRenderer interfaceRenderer, Node rootNode) {
        this.assetManager = assetManager;
        this.cam = cam;
        this.rootNode = rootNode;
        this.physicsControler = physicsControler;
        this.interfaceRenderer = interfaceRenderer;
        bulletRenderer = new BulletRenderer();
    }

    public void setUpKeys(InputManager inputManager) {
        //Mouse
        inputManager.addMapping(MAPPING_LEFT_CLICK, Trigger_LEFT_CLICK);
        inputManager.addMapping(MAPPING_RIGHT_CLICK, Trigger_RIGHT_CLICK);

        inputManager.addListener(new MouseListener(), MAPPING_LEFT_CLICK);
        inputManager.addListener(new MouseListener(), MAPPING_RIGHT_CLICK);

        //Keyboard
        inputManager.addMapping(MAPPING_RELOAD, Trigger_RELOAD);
        inputManager.addMapping(MAPPING_MENU, Trigger_MENU);

        inputManager.addListener(new KeyListener(), MAPPING_RELOAD);
        inputManager.addListener(new KeyListener(), MAPPING_MENU);
    }

    public void action(float tpf) {
        if (fireTimer <= 0 && !reloadPause) {
            if (MouseListener.left_click) {
                shootBullet();
                fireTimer = fireRate;
            }
            if (KeyListener.reload) {
                //Animation

                reload();
            }
            if (KeyListener.menu) {

            }
        }
        fireTimer -= tpf;
    }

    private void shootBullet() {
        if (PlayerData.CURRENT_AMMO > 0) {
            Spatial bullet = bulletRenderer.renderBullet(assetManager);
            bullet.setLocalTranslation(cam.getLocation().add(cam.getDirection()));

            GhostControl ghost = new GhostControl(new SphereCollisionShape(0.2f));
            RigidBodyControl bullet_physics = new RigidBodyControl(0.5f);

            bullet.addControl(bullet_physics);
            bullet.addControl(ghost);

            bulletNodes.attachChild(bullet);

            rootNode.attachChild(bulletNodes);
            physicsControler.addPhysicsObject(bullet);
            physicsControler.addPhysicsObject(ghost);

            bullet_physics.setLinearVelocity(cam.getDirection().mult(200));

            PlayerData.CURRENT_AMMO--;
        } else {
            reload();
        }
    }

    private void reload() {
        interfaceRenderer.setReloading(true);
        reloadPause = true;

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                PlayerData.CURRENT_AMMO = PlayerData.MAX_AMMO;
                reloadPause = false;
                bulletReadyToDelete = true;
                interfaceRenderer.setReloading(false);
            }
        }, 2500);

    }

    public void deleteBulletsAfterTime() {
        if (bulletReadyToDelete) {
            bulletNodes.detachAllChildren();

            bulletReadyToDelete = false;
        }
    }

}
