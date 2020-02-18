package com.mega.games.gamestartingkit.core.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mega.games.gamestartingkit.core.dataLoaders.GameAssetManager;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;

import java.util.Locale;

public class DebugRenderer {
    private Label fpsLabel;
    private Label elapsedLabel;

    private float elapsed;
    private float fpsElapsed;
    private float fpsRate;
    private float fpsSamples;

    public DebugRenderer(){
        fpsLabel = new Label("FPS:0", GameAssetManager.getInstance().scoreLabelStyle);
        fpsLabel.setPosition(0, GameData._virtualHeight - GameAssetManager.getInstance().scoreFontSize);
        fpsLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);

        elapsedLabel = new Label("Elapsed:0", GameAssetManager.getInstance().scoreLabelStyle);
        elapsedLabel.setPosition(0, GameData._virtualHeight - 2*GameAssetManager.getInstance().scoreFontSize);
        elapsedLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);
    }

    public void update(float dt) {
        fpsRate += 1/dt;
        fpsSamples++;
        fpsElapsed += dt;

        if(fpsElapsed > 0.2) {
            fpsLabel.setText(String.format(Locale.US,"FPS : %d", (int)(fpsRate/fpsSamples)));
            fpsSamples = 0;
            fpsRate = 0;
            fpsElapsed = 0;
        }

        elapsed += dt;
        elapsedLabel.setText(String.format(Locale.US,"Elapsed : %d", (int)elapsed));
    }

    public void draw(Batch batch) {
        fpsLabel.draw(batch,1);
        elapsedLabel.draw(batch,1);
    }
}
