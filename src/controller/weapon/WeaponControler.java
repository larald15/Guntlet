/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.weapon;

import com.jme3.scene.Node;
import handler.actions.ActionHandler;
import handler.movement.MovementHandler;

/**
 *
 * @author Larsen
 */
public class WeaponControler {

    private Node rootNode;
    private ActionHandler ah;
    private MovementHandler mh;

    public WeaponControler(Node rootNode, ActionHandler ah, MovementHandler mh) {
        this.rootNode = rootNode;
        this.ah = ah;
        this.mh = mh;
    }

}
