/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.bullet;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Larsen
 */
public class BulletRenderer {

//    public Geometry renderBullet(AssetManager assetManager) {
//        Sphere bulletSphere = new Sphere(20, 20, 0.20f);
//        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        material.setColor("Color", ColorRGBA.Black);
//
//        Geometry bulletGeometry = new Geometry("Bullet Sphere", bulletSphere);
//        bulletGeometry.setMaterial(material);
//
//        return bulletGeometry;
//    }

    public Spatial renderBullet(AssetManager assetManager) {
        Spatial bullet = assetManager.loadModel("Models/Bullets/stone.j3o");
        bullet.scale(0.5f);
        Material defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        bullet.setMaterial(defaultMaterial);

        return bullet;
    }

    public Geometry renderHitMarker(AssetManager assetManager) {
        Sphere hit = new Sphere(20, 20, 1f);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);

        Geometry hitGeometry = new Geometry("HitMarker", hit);
        hitGeometry.setMaterial(material);

        return hitGeometry;
    }

}
