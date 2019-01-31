/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer.ModelRenderer;

import com.jme3.scene.Spatial;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Larsen
 */
public class ModelRenderer {
    
    public void init(AssetManager assetManager) {
        Spatial Audi = assetManager.loadModel("Models/Audi R8.blend");
    }
    
}
