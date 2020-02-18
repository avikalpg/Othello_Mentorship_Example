package com.mega.games.gamestartingkit;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.MegaApplication;
import com.mega.games.android.AndroidGameUI;
import com.mega.games.gamestartingkit.core.GameStartingKit;
import com.mega.games.support.MegaServices;
import com.mega.games.support.ui.GameUIManager;

import kotlin.Unit;


public class MainActivity extends MegaApplication {
    GameStartingKit game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;

        MegaServices megaServ = new MegaServices();

        game = new GameStartingKit(megaServ);

        GameUIManager gameUI = new AndroidGameUI(this, megaServ);
        gameUI.setOnPlayAgainListener(() -> {
            game.restartGame();
            return Unit.INSTANCE;
        });

        gameUI.setOnQuitListener(() -> {
            finish();
            return Unit.INSTANCE;
        });

        game.setGameUIManager(gameUI);

        config.numSamples = 2;
        initialize(game,  config);
    }


    @Override
    public void onBackPressed() {
        if (game.isPlaying()) {
            game.forceGameOver();
        } else {
            super.onBackPressed();
        }
    }
}
