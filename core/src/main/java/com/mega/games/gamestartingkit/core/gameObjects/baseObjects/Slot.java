package com.mega.games.gamestartingkit.core.gameObjects.baseObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mega.games.gamestartingkit.core.dataLoaders.GameAssetManager;

public class Slot extends GameObject {
    //Member variables
    private TextureAtlas.AtlasRegion currReg;

    public Slot(float length, Color color) {
        currReg = GameAssetManager.getInstance().squareOutline;
        setSize(length, length);
        setColor(color);
    }

    public float getSlotSize() {
        return this.getSize().x;
    }

    @Override
    public void onTouchDown(float x, float y) {

    }

    @Override
    public void onTouchUp(float x, float y) {

    }

    @Override
    public void onTouchDragged(float x, float y) {

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(Batch batch) {
        batch.setColor(getColor());
        batch.draw(currReg,getPos().x,getPos().y,getSize().x, getSize().y);
        batch.setColor(new Color(1,1,1,1));
    }
}
