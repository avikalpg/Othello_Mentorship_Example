package com.mega.games.gamestartingkit.core.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;
import com.mega.games.gamestartingkit.core.gameObjects.entities.Ball;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.GameObject;
import com.mega.games.gamestartingkit.core.gameObjects.entities.Board;

import java.util.ArrayList;

public class GameObjectManager {
    private Board board;
    private ArrayList<GameObject> objs;

    public void reset(){
        //on reset, clear the object list and just add a ball
        objs.clear();

        float margin = 10f;
        float board_size = Math.min(GameData._virtualHeight, GameData._virtualWidth) - (2 * margin);
        board = new Board(8, board_size, Color.CORAL);
        board.setPos(GameData._virtualWidth/2f - board_size/2f, GameData._virtualHeight/2f - board_size/2f);

        objs.add(board);
    }

    public ArrayList<GameObject> getObjs() {
        return objs;
    }


    public void update(float dt){
        //Automatically called every frame before draw function
        for(GameObject obj:objs){
            obj.update(dt);
        }
    }

    public void draw(Batch batch){
        //automatically called every frame after update function
        for(GameObject obj:objs){
            obj.draw(batch);
        }
    }

    //set singleton
    private static final GameObjectManager _myInstance = new GameObjectManager();
    public static GameObjectManager getInstance(){
        return _myInstance;
    }
    private GameObjectManager(){
        objs = new ArrayList<>();
    }
}
