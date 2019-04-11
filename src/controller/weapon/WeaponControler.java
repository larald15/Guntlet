/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.weapon;

import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import handler.actions.ActionHandler;
import handler.movement.MovementHandler;
import java.util.ArrayList;

/**
 *
 * @author Larsen
 */
public class WeaponControler {

    private Node rootNode;
    private ActionHandler ah;
    private MovementHandler mh;

    private float time = 10f;
    private float counter = time;

    public WeaponControler(Node rootNode, ActionHandler ah, MovementHandler mh) {
        this.rootNode = rootNode;
        this.ah = ah;
        this.mh = mh;
    }

    //Vorläufige Methode - Verbesserungswürdig
    public void deleteBulletsAfterTime(float tpf) {
        if (counter <= 0) {
            for (Spatial spat : rootNode.getChildren()) {
                if (spat.getName().contains("Bullet Sphere")) {
                    spat.removeFromParent();
                }
            }
            counter = time;
        }
        counter -= tpf;
    }

    //Nico fix please blyat
    public void deleteSingleBullet() {
        ArrayList<Geometry> bulletList = ah.getBulletList();

        for (Geometry geo : bulletList) {
            RigidBodyControl geoRigid = (RigidBodyControl) geo.getControl(0);
            GhostControl ghost = (GhostControl) geo.getControl(1);
            System.out.println(ghost.getOverlappingObjects().toString());

            for (PhysicsCollisionObject overlappingObject : ghost.getOverlappingObjects()) {
//                    System.out.println("Player Collision: " + overlappingObject.getCollisionShape().toString().equals(mh.getPlayer().getCollisionShape().toString()));
//                    System.out.println("Bullet Collision: " + overlappingObject.getCollisionShape().toString().equals(geoRigid.getCollisionShape().toString()));
                if (!(overlappingObject.getCollisionShape().equals(geoRigid.getCollisionShape()))) {
                    if (!(overlappingObject.getCollisionShape().equals(mh.getPlayer().getCollisionShape()))) {
//                        System.out.println("Delete");
                    }
                }
//                    System.out.println("Bullet: " + geoRigid.getCollisionShape().toString());
//                    System.out.println("Player: " + mh.getPlayer().getCollisionShape().toString());
//                    System.out.println("Other: " + overlappingObject.getCollisionShape().toString());
            }
        }
    }

}
