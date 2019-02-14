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
    
    private final float playerRadius = 1.5f;
    private final float playerHeight = 6f;
    private final int playerAxis = 1;
    
    public void setUpCollision() {
        collisionShape = new CapsuleCollisionShape(playerRadius, playerHeight, playerAxis);
    }

    public CapsuleCollisionShape getCollisionShape() {
        return collisionShape;
    }

}
