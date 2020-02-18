package com.mega.games.gamestartingkit.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mega.games.gamestartingkit.core.dataLoaders.GameData;
import com.mega.games.gamestartingkit.core.dataLoaders.GameDataController;
import com.mega.games.gamestartingkit.core.dataLoaders.GameInfra;
import com.mega.games.gamestartingkit.core.dataLoaders.GameSoundManager;
import com.mega.games.gamestartingkit.core.dataLoaders.WorldData;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.GameObject;
import com.mega.games.gamestartingkit.core.gameObjects.GameObjectManager;
import com.mega.games.gamestartingkit.core.widgets.DebugRenderer;
import com.mega.games.gamestartingkit.core.widgets.HUD;

public class PlayScreen implements Screen {
    /*
    first frame takes time to load due to async asset loading. This causes
    a large update delta, so skip it.
    */
    private boolean skipFrame = true;
    private float timeSinceGameEnd;

    private DebugRenderer debugRenderer;
    private HUD hud;

    private Stage stage;

    public boolean valuesInitialized;

    public PlayScreen(){
        valuesInitialized = false;
        debugRenderer = new DebugRenderer();

        hud = new HUD();

        stage = new Stage(GameInfra.getInstance().viewport,GameInfra.getInstance().batch);
        Gdx.input.setInputProcessor(stage);

        addUIListeners();

        GameDataController.getInstance().startGame();
    }

    private void addUIListeners(){
        stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Handle touch down input
                if(GameObjectManager.getInstance().getObjs()!=null) {
                    for (GameObject obj : GameObjectManager.getInstance().getObjs()) {
                        obj.onTouchDown(x,y);
                    }
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //Handle touch up input
                if(GameObjectManager.getInstance().getObjs()!=null) {
                    for (GameObject obj : GameObjectManager.getInstance().getObjs()) {
                        obj.onTouchUp(x,y);
                    }
                }
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //Handle touch dragged input
                if(GameObjectManager.getInstance().getObjs()!=null) {
                    for (GameObject obj : GameObjectManager.getInstance().getObjs()) {
                        obj.onTouchDragged(x,y);
                    }
                }
                super.touchDragged(event, x, y, pointer);
            }
        });
    }

    @Override
    public void show() {

    }

    private void initIfNeeded() {
        if (!valuesInitialized) {
            //reset data and object managers
            //Todo : Add all reset and init functions here
            GameDataController.getInstance().setDefault();
            WorldData.getInstance().setDefault();
            GameObjectManager.getInstance().reset();
            valuesInitialized = true;
        }
    }

    private void update(float dt){
        initIfNeeded();

        GameDataController.getInstance().update(dt);

        //Add Game End Lag
        if(GameDataController.getInstance().getIsGameEnded() && !GameDataController.getInstance().getIsGameOver()){
            timeSinceGameEnd += dt;
            if(timeSinceGameEnd > GameData.getInstance().gameEndLag){
                GameDataController.getInstance().setGameOver();
                return;
            }
        }

        GameObjectManager.getInstance().update(dt);

        hud.update(dt);
        debugRenderer.update(dt);
        stage.act(dt);
    }

    @Override
    public void render(float delta) {
        //skip frame if needed
        if (skipFrame) {
            skipFrame = false;
            return;
        }

        //stop render loop if game is over
        if (!GameDataController.getInstance().getIsGameOver()) {
            //update Objects
            update(delta);
        }


        //Clear Screen and Buffers
        Gdx.gl.glClearColor(1,1,1,0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw Objects
        Batch batch = GameInfra.getInstance().batch;

        batch.begin();
//        hud.draw(batch);
        GameObjectManager.getInstance().draw(batch);

//        if(GameData._debugMode) {
//            debugRenderer.draw(batch);
//        }
        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        GameInfra.getInstance().resizeScreen(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
