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
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import data.PlayerData;

/**
 *
 * @author Larsen
 */
public class PlayerRenderer {

    private final Node rootNode;
    private Node playerArms;

    private Vector3f spawnCoordinates = new Vector3f(0, 1, 0);

    public PlayerRenderer(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Geometry setUpPlayer(AssetManager assetManager, Camera cam) {
        Box box1 = new Box(PlayerData.RADIUS, PlayerData.HEIGHT, PlayerData.AXIS);
        Geometry playerModel = new Geometry("Box", box1);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Yellow);   // set color of material to blue

        playerModel.setMaterial(mat);
        playerModel.setLocalTranslation(spawnCoordinates);

        rootNode.attachChild(playerModel);

        return playerModel;
    }

    public void setUpArms(AssetManager assetManager) {
        playerArms = (Node) assetManager.loadModel("Models/");
    }
    
    public void setUpGirl(AssetManager assetManager) {
        Spatial girl = assetManager.loadModel("Models/Girl/girl.j3o");
        girl.setLocalTranslation(0, 1, -15);
        girl.scale(5);
        Material defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        //defaultMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Girl/tartan.png"));
        girl.setMaterial(defaultMaterial);
        rootNode.attachChild(girl);
    }

    public void setUpPanzer(AssetManager assetManager) {
        Spatial panzer = assetManager.loadModel("Models/Panzer/IS4.blend");
        panzer.setLocalTranslation(0, 1, -17);
        panzer.scale(2);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Models/Girl/stripes.png"));
        panzer.setMaterial(mat);
    }
}
