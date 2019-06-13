/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.enemies;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Larsen
 */
public class EnemyHandler {
    
    private AssetManager assetManager;
    private Node rootNode;
    
    private int enemyCounter = 0;
    
    public EnemyHandler(Node rootNode, AssetManager assetManager) {
        this.assetManager = assetManager;
        this.rootNode = rootNode;
    }
    
    private Vector3f calculateCoordinates() {
        
        
        
        return null;
    }
    
    public void spawnEnemies() {
        Spatial enemy = assetManager.loadModel("Models/Girl/girl.j3o");
        enemy.setLocalTranslation(0, 1, -15);
        enemy.scale(4.5f);
        Material defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        //defaultMaterial.setTexture("ColorMap", assetManager.loadTexture("Models/Girl/tartan.png"));
        enemy.setMaterial(defaultMaterial);
        rootNode.attachChild(enemy);
    }
    
    public void moveEnemy() {
        
    }
    
}
