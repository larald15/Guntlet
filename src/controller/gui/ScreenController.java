/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.gui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import main.Main;

/**
 *
 * @author tobia
 */
public class ScreenController implements de.lessvoid.nifty.screen.ScreenController{

    public void startGame(){
        Main.started = true;
        System.out.println("Starting");
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }
    
}
