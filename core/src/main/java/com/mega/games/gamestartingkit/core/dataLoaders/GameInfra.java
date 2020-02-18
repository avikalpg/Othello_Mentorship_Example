package com.mega.games.gamestartingkit.core.dataLoaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.mega.games.gamestartingkit.core.graphics.shaders.ShaderManager;
import com.mega.games.support.MegaServices;

public class GameInfra {
    private static final GameInfra _myInstance = new GameInfra();
    public MegaServices megaServices;

    //Drawing Infra
    public SpriteBatch batch;
    public ShapeRenderer renderer;
    public FrameBuffer frameBuffer;

    //Viewport and Camera
    /*
        Note:
        Viewport in most cases will either be an extend viewport or scaling viewport.
        Be cautious if using other viewports for production
    */

    //Todo: Replace with Correct Viewport
    public FitViewport viewport;
    public OrthographicCamera cam;

    public void init(){
        //Viewport and Cam
        cam = new OrthographicCamera(GameData._virtualWidth,GameData._virtualHeight);
        cam.setToOrtho(false, GameData._virtualWidth, GameData._virtualHeight);
        viewport = new FitViewport(GameData._virtualWidth,GameData._virtualHeight,cam);

        cam.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);

        //Buffers, Batch and Renderer
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, GameData._virtualWidth,GameData._virtualHeight, true);

        //Assets
        AssetManager manager = new AssetManager(megaServices.fileResolver());
        setupTTFFontsLoader(manager);

        GameAssetManager.getInstance().init(manager);
        GameSoundManager.getInstance().init(manager);

        //Graphics
        //ShaderManager.getInstance().init(batch);

        //Physics
        //todo: Create physics engine
        /////////

        //Animation
        //todo: Create Animation Library
        ///////////

        //Data
        GameDataController.getInstance().setMegaServices(megaServices);

        //Mega Callback
        megaServices.callbacks().loaded();
    }

    public void resizeScreen(int width, int height){
        //Todo : Be sure to resize the components that depend on viewport

        //Scale and update viewport
        //Todo:Correct the viewport scaling and update code
        viewport.update(width,height);

        //update Batch and renderer's projection matrices
        batch.setProjectionMatrix(GameInfra.getInstance().cam.combined);
        renderer.setProjectionMatrix(GameInfra.getInstance().cam.combined);
    }

    private void setupTTFFontsLoader(AssetManager manager){
        //to be able to load ttf fonts
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(megaServices.fileResolver()));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(megaServices.fileResolver()));
    }

    public void setMegaServices(MegaServices megaServ){
        megaServices = megaServ;
    }

    public static GameInfra getInstance(){
        return _myInstance;
    }
}
