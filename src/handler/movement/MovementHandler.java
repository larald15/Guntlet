/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.movement;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import controller.player.PlayerControl;
import data.PlayerData;
import handler.collision.CollisionHandler;
import renderer.player.PlayerRenderer;

/**
 *
 * @author Larsen
 */
public class MovementHandler {

    private PlayerControl player;
    private final CollisionHandler collisionHandler;
    private final PlayerRenderer playerRenderer;
    private Geometry playerModel;
    private Node rootNode;

    //temporary variables
    private Vector3f walkDirection = new Vector3f();
    private Vector3f camDir = new Vector3f();
    private Vector3f camLeft = new Vector3f();

    //Key Triggers
    public static final Trigger TRIGGER_LEFT = new KeyTrigger(KeyInput.KEY_A);
    public static final Trigger TRIGGER_RIGHT = new KeyTrigger(KeyInput.KEY_D);
    public static final Trigger TRIGGER_FORWARD = new KeyTrigger(KeyInput.KEY_W);
    public static final Trigger TRIGGER_BACKWARDS = new KeyTrigger(KeyInput.KEY_S);
    public static final Trigger TRIGGER_JUMP = new KeyTrigger(KeyInput.KEY_SPACE);
    public static final Trigger TRIGGER_SPRINT = new KeyTrigger(KeyInput.KEY_LSHIFT);

    //Key Mappings
    public static final String MAPPING_LEFT = "Left";
    public static final String MAPPING_RIGHT = "Right";
    public static final String MAPPING_FORWARDS = "Forward";
    public static final String MAPPING_BACKWARDS = "Backward";
    public static final String MAPPING_JUMP = "Jump";
    public static final String MAPPING_SPRINT = "Sprint";

    public MovementHandler(CollisionHandler collisionHandler, Node rootNode, AssetManager assetManager, Camera cam) {
        this.collisionHandler = collisionHandler;
        playerRenderer = new PlayerRenderer(rootNode);

        playerModel = playerRenderer.setUpPlayer(assetManager, cam);
    }

    public void setUpPlayer(FlyByCamera flyCam) {
        CapsuleCollisionShape collisionShape = collisionHandler.getCollisionShape();

        player = new PlayerControl(collisionShape, PlayerData.STEP_HEIGHT);
        playerModel.addControl(player);

        player.setGravity(new Vector3f(0, PlayerData.GRAVITY, 0));
        player.warp(new Vector3f(0, 6f, 0));
    }

    public void setUpKeys(InputManager inputManager) {
        //Keyboard
        inputManager.addMapping(MAPPING_LEFT, TRIGGER_LEFT);
        inputManager.addMapping(MAPPING_RIGHT, TRIGGER_RIGHT);
        inputManager.addMapping(MAPPING_FORWARDS, TRIGGER_FORWARD);
        inputManager.addMapping(MAPPING_BACKWARDS, TRIGGER_BACKWARDS);
        inputManager.addMapping(MAPPING_JUMP, TRIGGER_JUMP);
        inputManager.addMapping(MAPPING_SPRINT, TRIGGER_SPRINT);

        inputManager.addListener(new KeyListener(), MAPPING_LEFT);
        inputManager.addListener(new KeyListener(), MAPPING_RIGHT);
        inputManager.addListener(new KeyListener(), MAPPING_FORWARDS);
        inputManager.addListener(new KeyListener(), MAPPING_BACKWARDS);
        inputManager.addListener(new KeyListener(), MAPPING_JUMP);
        inputManager.addListener(new KeyListener(), MAPPING_SPRINT);
    }

    public void move(Camera cam) {
        camDir.set(cam.getDirection()).multLocal(PlayerData.SPEED, 0.0f, PlayerData.SPEED);
        camLeft.set(cam.getLeft()).multLocal(PlayerData.STRAFE_SPEED);
        walkDirection.set(0, 0, 0);

        CollisionResults collisions = new CollisionResults();

        if (KeyListener.left) {
            walkDirection.addLocal(camLeft);
        }
        if (KeyListener.right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (KeyListener.forwards) {
            walkDirection.addLocal(camDir);
        }
        if (KeyListener.backwards) {
            walkDirection.addLocal(camDir.negate());
        }
        if (KeyListener.jump) {
            if (player.onGround()) {
                player.jump(new Vector3f(0, PlayerData.JUMP_FORCE, 0));
            }
        }

        player.setWalkDirection(walkDirection);
        cam.setLocation(new Vector3f(playerModel.getLocalTranslation().x, playerModel.getLocalTranslation().y + PlayerData.EYEHEIGHT,
                playerModel.getLocalTranslation().z));
    }

    public CharacterControl getPlayer() {
        return player;
    }

}
