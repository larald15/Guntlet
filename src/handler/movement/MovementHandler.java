/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.movement;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import handler.collision.CollisionHandler;

/**
 *
 * @author Larsen
 */
public class MovementHandler {

    private CharacterControl player;
    private final CollisionHandler collisionHandler;
    
    //Triggers
    public static final Trigger TRIGGER_LEFT = new KeyTrigger(KeyInput.KEY_A);
    public static final Trigger TRIGGER_RIGHT = new KeyTrigger(KeyInput.KEY_D);
    public static final Trigger TRIGGER_FORWARD = new KeyTrigger(KeyInput.KEY_W);
    public static final Trigger TRIGGER_BACKWARDS = new KeyTrigger(KeyInput.KEY_S);
    public static final Trigger TRIGGER_JUMP = new KeyTrigger(KeyInput.KEY_SPACE);

    //Mappings
    public static final String MAPPING_LEFT = "Left";
    public static final String MAPPING_RIGHT = "Right";
    public static final String MAPPING_FORWARDS = "Forward";
    public static final String MAPPING_BACKWARDS = "Backward";
    public static final String MAPPING_JUMP = "Jump";

    //temporary variables
    private Vector3f walkDirection = new Vector3f();
    private Vector3f camDir = new Vector3f();
    private Vector3f camLeft = new Vector3f();

    //Player Variables
    private final float playerSpeed = -1f;

    public MovementHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    } 
    
    public void setUpPlayer(FlyByCamera flyCam) {
        CapsuleCollisionShape collisionShape = collisionHandler.getCollisionShape();
        
        flyCam.setMoveSpeed(playerSpeed);

        player = new CharacterControl(collisionShape, 0.1f);
        player.setGravity(new Vector3f(0, -20f, 0));
        player.warp(new Vector3f(0, 5f, 0));
    }

    public void setUpKeys(InputManager inputManager) {
        inputManager.addMapping(MAPPING_LEFT, TRIGGER_LEFT);
        inputManager.addMapping(MAPPING_RIGHT, TRIGGER_RIGHT);
        inputManager.addMapping(MAPPING_FORWARDS, TRIGGER_FORWARD);
        inputManager.addMapping(MAPPING_BACKWARDS, TRIGGER_BACKWARDS);
        inputManager.addMapping(MAPPING_JUMP, TRIGGER_JUMP);

        inputManager.addListener(new KeyListener(), MAPPING_LEFT);
        inputManager.addListener(new KeyListener(), MAPPING_RIGHT);
        inputManager.addListener(new KeyListener(), MAPPING_FORWARDS);
        inputManager.addListener(new KeyListener(), MAPPING_BACKWARDS);
        inputManager.addListener(new KeyListener(), MAPPING_JUMP);
    }

    public void move(Camera cam) {
        camDir.set(cam.getDirection()).multLocal(0.6f);
        camLeft.set(cam.getLeft()).multLocal(0.4f);
        walkDirection.set(0, 0, 0);

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
            player.jump(new Vector3f(0, 15f, 0));
        }

        player.setWalkDirection(walkDirection);
        cam.setLocation(player.getPhysicsLocation());
    }

    public CharacterControl getPlayer() {
        return player;
    }

}
