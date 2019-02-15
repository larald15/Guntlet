/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.interfaces;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

/**
 *
 * @author Larsen
 */
public class InterfaceRenderer {

    private Node rootNode;
    private AssetManager assetManager;
    private BitmapFont guiFont;
    private AppSettings settings;
    private Node guiNode;

    public InterfaceRenderer(Node rootNode, AssetManager assetManager, BitmapFont guiFont, AppSettings settings, Node guiNode) {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.guiFont = guiFont;
        this.settings = settings;
        this.guiNode = guiNode;
    }

    public void renderCrosshair() {
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+");
        ch.setLocalTranslation(settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }

}
