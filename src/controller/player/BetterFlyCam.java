/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.player;

import com.jme3.input.FlyByCamera;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author Larsen
 */
public class BetterFlyCam extends FlyByCamera {

    public BetterFlyCam(Camera cam) {
        super(cam);
    }

    @Override
    protected void rotateCamera(float value, Vector3f axis) {
        if (enabled) {
            Matrix3f mat = new Matrix3f();
            mat.fromAngleNormalAxis(rotationSpeed * value, axis);

            Vector3f up = cam.getUp();
            Vector3f left = cam.getLeft();
            Vector3f dir = cam.getDirection();

            mat.mult(up, up);
            mat.mult(left, left);
            mat.mult(dir, dir);

            if (up.getY() < 0) {
                return;
            }

            Quaternion q = new Quaternion();
            q.fromAxes(left, up, dir);
            q.normalizeLocal();
            cam.setAxes(q);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
