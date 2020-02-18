package com.mega.games.gamestartingkit.core.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mega.games.gamestartingkit.core.dataLoaders.GameAssetManager;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;

public class HUD{
    private Label scoreLabel;

    public HUD(){
        scoreLabel = new Label("0", GameAssetManager.getInstance().scoreLabelStyle);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);
        scoreLabel.setPosition(0,GameData._virtualHeight - 2*scoreLabel.getHeight());
    }

    public void update(float dt) {
        //increase score by 1 every second
        GameData.getInstance().score += dt;

        scoreLabel.setText(Long.toString((long)GameData.getInstance().score));
    }

    public void draw(Batch batch) {
        scoreLabel.draw(batch,1);
    }
}
