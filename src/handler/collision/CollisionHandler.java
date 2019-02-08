/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.collision;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;

/**
 *
 * @author Larsen
 */
public class CollisionHandler {

    private CapsuleCollisionShape collisionShape;

    public void setUpCollision() {
        collisionShape = new CapsuleCollisionShape(1.5f, 6f, 1);
    }

    public CapsuleCollisionShape getCollisionShape() {
        return collisionShape;
    }

}
