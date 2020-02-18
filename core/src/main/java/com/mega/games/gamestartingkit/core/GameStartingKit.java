package com.mega.games.gamestartingkit.core;

import com.mega.games.gamestartingkit.core.dataLoaders.GameDataController;
import com.mega.games.gamestartingkit.core.dataLoaders.GameInfra;
//import com.mega.games.gamestartingkit.core.examples.shaders.ShaderExampleScreen1;
import com.mega.games.gamestartingkit.core.screens.PlayScreen;
import com.mega.games.support.MegaGame;
import com.mega.games.support.MegaServices;

public class GameStartingKit extends MegaGame {

    private MegaServices megaServices;

    public GameStartingKit(MegaServices megaServices){
        super(megaServices);
        this.megaServices = megaServices;
    }

    @Override
    public void create() {
        super.create();

        //setup game infra
        GameInfra.getInstance().setMegaServices(megaServices);
        GameInfra.getInstance().init();

        //start the game
        restartGame();
    }

    @Override
    public void resize(int width, int height) {
        GameInfra.getInstance().resizeScreen(width, height);
    }

    @Override
    public void forceGameOver() {
//        GameDataController.getInstance().setGameOver();
    }

    @Override
    public void restartGame() {
        if(getScreen() != null){
            getScreen().dispose();
            setScreen(null);
        }

        //set screen to play screen
        setScreen(new PlayScreen());
    }
}
