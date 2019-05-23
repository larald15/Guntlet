/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Larsen
 */
public class PlayerData {

    //Movement
    public static final float SPEED = 0.5f;
    public static final float STRAFE_SPEED = SPEED;
    public static final float SPRINT_SPEED = 0.8f;
    public static final float SPRINT_STRAFE_SPEED = SPRINT_SPEED;
    public static final float GRAVITY = -100f;
    public static final float JUMP_FORCE = 33f;
    public static final float STEP_HEIGHT = 0.1f;
    public static final float MASS = 1f;

    //Render
    public static final float RADIUS = 1.5f;
    public static final float HEIGHT = 4f;
    public static final float EYEHEIGHT = 3f;
    public static final int AXIS = 1;

    //Stats
    public static final int MAX_HEALTH = 100;
    public static final int MAX_AMMO = 20;

    public static int CURRENT_HEALTH = 100;
    public static int CURRENT_AMMO = 20;

}
