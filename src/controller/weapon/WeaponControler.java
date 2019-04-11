/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.weapon;

import com.jme3.bullet.objects.PhysicsGhostObject;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import controller.physics.PhysicsControler;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Larsen
 */
public class WeaponControler {

    private Node rootNode;
    private PhysicsControler pc;

    private float time = 10f;
    private float counter = time;

    public WeaponControler(Node rootNode, PhysicsControler pc) {
        this.rootNode = rootNode;
        this.pc = pc;
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

    public void deleteSingleBullet() {
        Collection<PhysicsGhostObject> ghostList = pc.getGhostObjects();
        
        for (PhysicsGhostObject ghost : ghostList) {
            if (ghost.getOverlappingCount() > 0) {
                
            }
        }
    }

}
