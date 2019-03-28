/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.actions;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import controller.physics.PhysicsControler;
import handler.movement.MouseListener;
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

    private CollisionResults collisionResults = new CollisionResults();

    private float fireRate = 0.15f;
    private float fireTimer = fireRate;

    //Mouse Triggers
    public static final Trigger Trigger_LEFT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    public static final Trigger Trigger_RIGHT_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);

    //Mouse Mappings
    public static final String MAPPING_LEFT_CLICK = "Left Click";
    public static final String MAPPING_RIGHT_CLICK = "Right Click";

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
    }

    public void action(float tpf) {
        if (fireTimer <= 0) {
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

        rootNode.attachChild(bullet);

        RigidBodyControl bullet_physics = new RigidBodyControl(0.5f);

        bullet.addControl(bullet_physics);
        physicsControler.addPhysicsObject(bullet);

        bullet_physics.setLinearVelocity(cam.getDirection().mult(650));
        //bullet_physics.setLinearVelocity(cam.getDirection().mult(50));

        /* Check for hit
        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
        rootNode.collideWith(ray, collisionResults);

        System.out.println(collisionResults.getClosestCollision().getGeometry().toString());

        
        CollisionResult firstHit = collisionResults.getClosestCollision();
        Geometry hitmarker = bulletRenderer.renderHitMarker(assetManager);
        hitmarker.setLocalTranslation(firstHit.getGeometry().getLocalTranslation());
        rootNode.attachChild(hitmarker);
         */
    }

}
