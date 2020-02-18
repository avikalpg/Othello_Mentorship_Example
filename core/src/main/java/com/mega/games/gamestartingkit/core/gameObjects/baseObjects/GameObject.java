package com.mega.games.gamestartingkit.core.gameObjects.baseObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    //properties
    private Vector2 pos;
    private Vector2 size;
    private Color color;

    public GameObject(){
        //properties
        pos = new Vector2();
        size = new Vector2();
        color=new Color();
    }

    //Setters and Getters
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setPos(float x, float y){
        this.pos.x = x;
        this.pos.y = y;
    }

    public Vector2 getPos(){
        return pos;
    }

    public void setSize(float sizeX, float sizeY){
        size.x = sizeX;
        size.y = sizeY;
    }

    public Vector2 getSize(){
        return size;
    }

    //these functions are called by PlayScreen class for all GameObjects for handling specific input
    public abstract void onTouchDown(float x, float y);
    public abstract void onTouchUp(float x, float y);
    public abstract void onTouchDragged(float x, float y);

    //update and draw functions
    public abstract void update(float dt);
    public abstract void draw(Batch batch);
}
