package com.mega.games.gamestartingkit.core.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mega.games.gamestartingkit.core.dataLoaders.GameAssetManager;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;

public class HUD{
    private Label blackScoreLabel;
    private Label whiteScoreLabel;
    private Label nextTurnLabel;

    public HUD(){
        blackScoreLabel = new Label("0", GameAssetManager.getInstance().scoreLabelStyle);
        blackScoreLabel.setAlignment(Align.left);
        blackScoreLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);
        blackScoreLabel.setPosition(0,GameData._virtualHeight - 2* blackScoreLabel.getHeight());

        whiteScoreLabel = new Label("0", GameAssetManager.getInstance().scoreLabelStyle);
        whiteScoreLabel.setAlignment(Align.right);
        whiteScoreLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);
        whiteScoreLabel.setPosition(0,GameData._virtualHeight - 2* whiteScoreLabel.getHeight());

        nextTurnLabel = new Label("Next Turn:", GameAssetManager.getInstance().scoreLabelStyle);
        nextTurnLabel.setAlignment(Align.center);
        nextTurnLabel.setSize(GameData._virtualWidth,GameAssetManager.getInstance().scoreFontSize);
        nextTurnLabel.setPosition(0,2* whiteScoreLabel.getHeight() );
    }

    public void update(float dt) {
        blackScoreLabel.setText(Long.toString((long)GameData.getInstance().black_score));
        whiteScoreLabel.setText(Long.toString((long)GameData.getInstance().white_score));
    }

    public void draw(Batch batch) {
        blackScoreLabel.draw(batch,1);
        whiteScoreLabel.draw(batch,1);
        nextTurnLabel.draw(batch,1);
    }
}
