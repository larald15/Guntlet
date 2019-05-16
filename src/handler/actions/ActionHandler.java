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
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import controller.physics.PhysicsControler;
import controller.weapon.WeaponControler;
import data.PlayerData;
import handler.movement.MouseListener;
import java.util.ArrayList;
import renderer.bullet.BulletRenderer;

/**
 *
 * @author Larsen
 */
public class ActionHandler {

    private BulletRenderer bulletRenderer;
    private AssetManager assetManager;
    private Camera cam;
    private Node rootNode;

    private PhysicsControler physicsControler;
    private WeaponControler weaponControler;

    private final float fireRate = 0.15f;
    private float fireTimer = fireRate;

    public ArrayList<Geometry> bulletList = new ArrayList<>();

    //Mouse Triggers
    public static final Trigger Trigger_LEFT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    public static final Trigger Trigger_RIGHT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);
    public static final Trigger Trigger_RELOAD = new MouseButtonTrigger(KeyInput.KEY_R);

    //Mouse Mappings
    public static final String MAPPING_LEFT_CLICK = "Left Click";
    public static final String MAPPING_RIGHT_CLICK = "Right Click";
    public static final String MAPPING_RELOAD = "Reload";

    public ActionHandler(AssetManager assetManager, Camera cam, PhysicsControler physicsControler, Node rootNode) {
        this.assetManager = assetManager;
        this.cam = cam;
        this.rootNode = rootNode;
        this.physicsControler = physicsControler;

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
        
        inputManager.addListener(new MouseListener(), MAPPING_RELOAD);
    }

    public void action(float tpf) {
        if (fireTimer <= 0) {
            if (MouseListener.left_click) {
                shootBullet();
                fireTimer = fireRate;
            } else if (MouseListener.reload) {
                //Animation + Pause Shooting
                reload();
            }
        }
        fireTimer -= tpf;
    }

    private void shootBullet() {
        if (PlayerData.CURRENT_AMMO > 0) {
            Geometry bullet = bulletRenderer.renderBullet(assetManager);
            bullet.setLocalTranslation(cam.getLocation().add(cam.getDirection()));

            GhostControl ghost = new GhostControl(new SphereCollisionShape(0.2f));
            RigidBodyControl bullet_physics = new RigidBodyControl(0.5f);

            bullet.addControl(bullet_physics);
            bullet.addControl(ghost);

            rootNode.attachChild(bullet);
            physicsControler.addPhysicsObject(bullet);
            physicsControler.addPhysicsObject(ghost);

            bullet_physics.setLinearVelocity(cam.getDirection().mult(50));
            bulletList.add(bullet);

            PlayerData.CURRENT_AMMO--;
        } else {
            reload();
        }
    }

    private void reload() {
        PlayerData.CURRENT_AMMO = PlayerData.MAX_AMMO;
    }
    
    public ArrayList<Geometry> getBulletList() {
        return bulletList;
    }

}
