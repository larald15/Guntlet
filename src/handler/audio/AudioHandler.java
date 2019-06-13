/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.audio;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;

/**
 *
 * @author Larsen
 */
public class AudioHandler {

    private AssetManager assetManager;

    public AudioHandler(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setupMusic() {
        AudioNode hardbass = new AudioNode(assetManager, "Sounds/hardbass.ogg", DataType.Stream);
        hardbass.setPositional(false);
        hardbass.setVolume(0.1f);
        hardbass.setLooping(true);
        hardbass.play();
    }
}
