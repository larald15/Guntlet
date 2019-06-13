/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler.sounds;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;

/**
 *
 * @author Larsen
 */
public class BackgroundMusic {

    private AssetManager assetManager;
    
    public BackgroundMusic (AssetManager assetManager){
        this.assetManager = assetManager;
    }
    
    public void setUpHardBass() {
        AudioNode hardbass = new AudioNode(assetManager, "Sounds/Music/hardbass.ogg", DataType.Stream);
        hardbass.setPositional(false);
        hardbass.setVolume(0.1f);
        hardbass.setLooping(true);
        hardbass.play();
    }

}
