/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.physics;

import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.objects.PhysicsGhostObject;
import com.jme3.input.FlyByCamera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import handler.movement.MovementHandler;
import java.util.Collection;
import renderer.map.MapRenderer;

/**
 *
 * @author Larsen
 */
public class PhysicsControler {

    private BulletAppState bulletAppState;
    private MapRenderer mapRenderer;
    private MovementHandler movementHandler;

    public PhysicsControler(BulletAppState bulletAppState, MovementHandler movementHandler, MapRenderer mapRenderer) {
        this.bulletAppState = bulletAppState;
        this.mapRenderer = mapRenderer;
        this.movementHandler = movementHandler;
    }

    public void setUpPhysics(AppStateManager stateManager, Node rootNode, AssetManager assetManager, ViewPort viewPort, FlyByCamera flyCam) {
        stateManager.attach(bulletAppState);

        bulletAppState.getPhysicsSpace().add(mapRenderer.getLandscape());
        bulletAppState.getPhysicsSpace().add(movementHandler.getPlayer());
        bulletAppState.startPhysics();
    }

    public void addPhysicsObject(Object obj) {
        bulletAppState.getPhysicsSpace().add(obj);
    }

    public Collection<PhysicsGhostObject> getGhostObjects() {
        return bulletAppState.getPhysicsSpace().getGhostObjectList();
    }

}
