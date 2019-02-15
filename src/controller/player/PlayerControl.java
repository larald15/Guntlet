/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.player;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;

/**
 *
 * @author Larsen
 */
public class PlayerControl extends CharacterControl {

    protected boolean onGround = false;

    public PlayerControl(CollisionShape shape, float stepHeight) {
        super(shape, stepHeight);
    }

}
