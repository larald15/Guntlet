/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.map;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Larsen
 */
public class MapRenderer {

    private final Node rootNode;
    private final AssetManager assetManager;
    private final ViewPort viewPort;

    private Spatial sceneModel;
    private RigidBodyControl landscape;

    private float velocity = 0.08f;
    private float ypos = 0;
    private float mag = 0.3f;
    
    public MapRenderer(Node rootNode, AssetManager assetManager, ViewPort viewPort) {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.viewPort = viewPort;
    }

    public void setUpMap() {
        assetManager.registerLocator("town.zip", ZipLocator.class);
        sceneModel = assetManager.loadModel("main.scene");
        sceneModel.setLocalScale(2f);
        sceneModel.setLocalTranslation(0, 0f, 0);

        //Map Collision
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);

        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));

        rootNode.attachChild(sceneModel);
    }

    public void setUpLight() {
        // We add light so we see the scene
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }

    public void shakeMap() {
        if (ypos < (- 2 * mag) || ypos > 0) {
            velocity *= -1;
        }
        
        sceneModel.setLocalTranslation(0, ypos, 0);
        ypos += velocity;
    }

    public Spatial getSceneModel() {
        return sceneModel;
    }

    public RigidBodyControl getLandscape() {
        return landscape;
    }

}
