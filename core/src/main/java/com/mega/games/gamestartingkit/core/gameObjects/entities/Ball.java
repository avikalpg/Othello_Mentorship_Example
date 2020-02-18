package com.mega.games.gamestartingkit.core.gameObjects.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.Circle;

public class Ball extends Circle {

    private Vector2 vel;
    private Vector2 acc;

    public Ball(float radius, Color color) {
        super(radius, color);
        vel = new Vector2();

        float gravity = -500f;
        acc = new Vector2(0,gravity);
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

    public void checkCollisions(){
        if(getPos().y < getRadius()){
            vel.y *= -1;
            setPos(getPos().x, getRadius());
        }
    }

    @Override
    public void update(float dt) {
        //update velocity based on acceleration
        vel.x += acc.x * dt;
        vel.y += acc.y * dt;

        //update pos based on velocity
        setPos(getPos().x + vel.x * dt, getPos().y + vel.y * dt);

        checkCollisions();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
