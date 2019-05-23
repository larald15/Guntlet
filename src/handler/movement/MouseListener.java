/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.movement;

import com.jme3.input.controls.ActionListener;
import handler.actions.ActionHandler;

/**
 *
 * @author Larsen
 */
public class MouseListener implements ActionListener {

    public static boolean left_click = false;
    public static boolean right_click = false;

    public static float time;

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals(ActionHandler.MAPPING_LEFT_CLICK)) {
            left_click = isPressed;
        } else if (name.equals(ActionHandler.MAPPING_RIGHT_CLICK)) {
            right_click = isPressed;
        }
    }

}
