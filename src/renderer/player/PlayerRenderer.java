/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.player;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Larsen
 */
public class PlayerRenderer {

    private Node rootNode;

    public Node playerNode;

    public PlayerRenderer(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void setUpPlayer(AssetManager assetManager) {
        playerNode = new Node("PlayerNode");
        
        Box box1 = new Box(1, 1, 1);
        Geometry playerBox = new Geometry("Box", box1);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        playerBox.setMaterial(mat);
        playerBox.setLocalTranslation(new Vector3f(0, 10f, 0));
        
        playerNode.attachChild(playerBox);
        rootNode.attachChild(playerNode);
    }

    public Node getPlayerNode() {
        return playerNode;
    }

}
