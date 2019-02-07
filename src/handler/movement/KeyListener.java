/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.movement;

import com.jme3.input.controls.ActionListener;

/**
 *
 * @author Larsen
 */
public class KeyListener implements ActionListener {

    public static boolean left = false, right = false, forwards = false, backwards = false, jump = false;

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals(MovementHandler.MAPPING_LEFT)) {
            left = isPressed;
        } else if (name.equals(MovementHandler.MAPPING_RIGHT)) {
            right = isPressed;
        } else if (name.equals(MovementHandler.MAPPING_FORWARDS)) {
            forwards = isPressed;
        } else if (name.equals(MovementHandler.MAPPING_BACKWARDS)) {
            backwards = isPressed;
        } else if (name.equals(MovementHandler.MAPPING_JUMP)) {
            jump = isPressed;
        }
    }

}
