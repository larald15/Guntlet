/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.movement;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;

/**
 *
 * @author Larsen
 */
public class MovementInit {

    //Triggers
    private static final Trigger TRIGGER_LEFT = new KeyTrigger(KeyInput.KEY_A);
    private static final Trigger TRIGGER_RIGHT = new KeyTrigger(KeyInput.KEY_D);
    private static final Trigger TRIGGER_FORWARD = new KeyTrigger(KeyInput.KEY_W);
    private static final Trigger TRIGGER_BACKWARDS = new KeyTrigger(KeyInput.KEY_S);
    private static final Trigger TRIGGER_JUMP = new KeyTrigger(KeyInput.KEY_SPACE);

    //Mappings
    private static final String MAPPING_LEFT = "Left";
    private static final String MAPPING_RIGHT = "Right";
    private static final String MAPPING_FORWARDS = "Forward";
    private static final String MAPPING_BACKWARDS = "Backward";
    private static final String MAPPING_JUMP = "Jump";

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

}
