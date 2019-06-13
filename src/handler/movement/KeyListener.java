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
public class KeyListener implements ActionListener {

    public static boolean left = false;
    public static boolean right = false;
    public static boolean forwards = false;
    public static boolean backwards = false;
    public static boolean jump = false;
    public static boolean sprint = false;
    public static boolean reload = false;
    public static boolean menu = false;

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
        } else if (name.equals(MovementHandler.MAPPING_SPRINT)) {
            sprint = isPressed;
        } else if (name.equals(MovementHandler.MAPPING_JUMP)) {
            jump = isPressed;
        } else if (name.equals(ActionHandler.MAPPING_RELOAD)) {
            reload = isPressed;
        } else if (name.equals(ActionHandler.MAPPING_MENU)) {
            menu = isPressed;
        }
    }

}
