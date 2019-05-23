/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.interfaces;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import data.PlayerData;

/**
 *
 * @author Larsen
 */
public class InterfaceRenderer {

    private Node rootNode;
    private AssetManager assetManager;
    private AppSettings settings;
    private Node guiNode;

    private BitmapText hudHealthAmmo;

    public InterfaceRenderer(Node rootNode, AssetManager assetManager, AppSettings settings, Node guiNode) {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.settings = settings;
        this.guiNode = guiNode;
        
        initHUD();
    }

    private void initHUD() {
        BitmapFont interfaceFont = assetManager.loadFont("Interface/Fonts/Allstar.fnt");
        hudHealthAmmo = new BitmapText(interfaceFont, false);
        hudHealthAmmo.setColor(ColorRGBA.Black);
        hudHealthAmmo.setText("Health: " + PlayerData.CURRENT_HEALTH + "\n"
                + "Ammo: " + PlayerData.CURRENT_AMMO);
        hudHealthAmmo.setLocalTranslation(20, 90, 0);
        guiNode.attachChild(hudHealthAmmo);
    }

    public void renderCrosshair() {
        BitmapFont crosshairFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(crosshairFont, false);
        ch.setSize(crosshairFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+");
        ch.setLocalTranslation(settings.getWidth() / 2 - crosshairFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }
    
    public void refreshHUD() {
        hudHealthAmmo.setText("Health: " + PlayerData.CURRENT_HEALTH + "\n"
                + "Ammo: " + PlayerData.CURRENT_AMMO);
    }

    public void showMessage(String text, float seconds) {
        BitmapFont interfaceFont = assetManager.loadFont("Interface/Fonts/College.fnt");
        BitmapText message = new BitmapText(interfaceFont);
        message.setText(text);
        message.setAlignment(BitmapFont.Align.Center);
        message.setLocalTranslation(settings.getWidth() / 2, settings.getHeight() / 2, 0);
        guiNode.attachChild(message);
    }

}
