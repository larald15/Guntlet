package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Spatial mug = assetManager.loadModel("Models/Mug-red/mug-red.j3o");
        Material test = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        test.setTexture("ColorMap", assetManager.loadTexture("Models/Mug-red/Mug-red.png"));
        
        mug.setMaterial(test);
        rootNode.attachChild(mug);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
