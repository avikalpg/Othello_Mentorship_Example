package com.mega.games.gamestartingkit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mega.games.gamestartingkit.core.GameStartingKit;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;
import com.mega.games.support.stub.MegaServices;
import com.mega.games.support.ui.GameUIManager;
import com.mega.games.support.ui.LibGdxGameUI;

import kotlin.Unit;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "GameStartingKit";
        config.width = GameData._virtualWidth;
        config.height = GameData._virtualHeight;

        config.resizable = true;

        config.samples = 2;

        MegaServices services = new MegaServices();
        GameStartingKit game = new GameStartingKit(services);

        GameUIManager gameUI = new LibGdxGameUI(services);
        gameUI.setOnPlayAgainListener(() -> {
            game.restartGame();
            return Unit.INSTANCE;
        });
        gameUI.setOnQuitListener(()->{
            System.exit(0);
            return Unit.INSTANCE;
        });

        game.setGameUIManager(gameUI);

        new LwjglApplication(game, config);
    }
}