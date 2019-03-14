/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.actions;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.Timer;
import handler.movement.MouseListener;
import java.util.ArrayList;
import java.util.List;
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

    private BulletAppState bulletAppState;
    
    private float fireRate = 0.1f;
    private float fireTimer = fireRate;

    //Mouse Triggers
    public static final Trigger Trigger_LEFT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    public static final Trigger Trigger_RIGHT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);

    //Mouse Mappings
    public static final String MAPPING_LEFT_CLICK = "Left Click";
    public static final String MAPPING_RIGHT_CLICK = "Right Click";
    
    public Node bulletNode = new Node();

    public ActionHandler(AssetManager assetManager, Camera cam, BulletAppState bulletAppState, Node rootNode) {
        this.assetManager = assetManager;
        this.cam = cam;
        this.bulletAppState = bulletAppState;
        this.rootNode = rootNode;

        bulletRenderer = new BulletRenderer();
    }

    public void setUpKeys(InputManager inputManager) {
        //Mouse
        inputManager.addMapping(MAPPING_LEFT_CLICK, Trigger_LEFT_CLICK);
        inputManager.addMapping(MAPPING_RIGHT_CLICK, Trigger_RIGHT_CLICK);

        inputManager.addListener(new MouseListener(), MAPPING_LEFT_CLICK);
        inputManager.addListener(new MouseListener(), MAPPING_RIGHT_CLICK);
    }

    public void action(float tpf) {
        if(fireTimer <= 0){
            if (MouseListener.left_click) {
                shootBullet();
                fireTimer = fireRate;
            }
        }
        fireTimer -= tpf;
    }

    private void shootBullet() {
        Geometry bullet = bulletRenderer.renderBullet(assetManager);
        bullet.setLocalTranslation(cam.getLocation().add(cam.getDirection()));

        rootNode.attachChild(bulletNode);
        bulletNode.attachChild(bullet);

        RigidBodyControl bullet_physics = new RigidBodyControl(0.5f);

        bullet.addControl(bullet_physics);
        bulletAppState.getPhysicsSpace().add(bullet_physics);

        bullet_physics.setLinearVelocity(cam.getDirection().mult(100));
        //bullet_physics.applyImpulse(cam.getDirection().mult(75), cam.getLocation());
    }
    
}
