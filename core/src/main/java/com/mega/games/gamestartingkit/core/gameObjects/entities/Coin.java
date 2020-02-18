package com.mega.games.gamestartingkit.core.gameObjects.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.Circle;

public class Coin extends Circle {

    public Coin(float radius, Color color) {
        super(radius, color);
    }

    @Override
    public void onTouchDown(float x, float y) {
        super.onTouchDown(x, y);
    }

    @Override
    public void onTouchUp(float x, float y) {
        super.onTouchUp(x, y);
    }

    @Override
    public void onTouchDragged(float x, float y) {
        super.onTouchDragged(x, y);
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
