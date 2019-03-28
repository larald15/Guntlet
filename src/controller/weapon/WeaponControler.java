/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.weapon;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Larsen
 */
public class WeaponControler {

    private Node rootNode;

    private float time = 10f;
    private float counter = time;

    public WeaponControler(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void deleteBullets(float tpf) {
        if (counter <= 0) {
            for (Spatial spat : rootNode.getChildren()) {
                if (spat.getName().equals("Bullet Sphere")) {
                    spat.removeFromParent();
                }
            }
            counter = time;
        }
        counter -= tpf;
    }

}
