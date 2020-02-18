package com.mega.games.gamestartingkit.core.dataLoaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSoundManager {
    private AssetManager manager;

    //Music
    private Music bg;

    //Sfx
    private Sound tap;

    public void init(AssetManager manager){
        this.manager = manager;

        loadAssets();

        manager.finishLoading();

        getAssets();
    }

    private void loadAssets(){
        manager.load("sounds/bg.mp3", Music.class);

        manager.load("sounds/tap.mp3", Sound.class);
    }

    private void getAssets(){
        bg = manager.get("sounds/bg.mp3", Music.class);
        bg.setVolume(0.3f);

        tap = manager.get("sounds/tap.mp3", Sound.class);
    }

    public void playBG(){
        bg.play();
    }

    public void stopBG(){
        bg.stop();
    }

    public void playTap(){
        tap.play();
    }

    //set singleton
    private static final GameSoundManager _myInstance = new GameSoundManager();
    public static GameSoundManager getInstance()
    {
        return _myInstance;
    }
    private GameSoundManager(){}
}
