package com.mega.games.gamestartingkit.core.dataLoaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GameData {
    //Basic Game Data
    private static int _screenFactor = 40;
    public static int _virtualWidth = 9 * _screenFactor;
    public static int _virtualHeight = 16 * _screenFactor;
    public static boolean _debugMode = true;

    public boolean isGameEnded;
    public boolean isGameOver;
    public float gameEndLag;

    public float elapsed;
    public float black_score;
    public float white_score;

    //Game Specific Data (Mostly Config Driven)
    //Todo: Define Game Specific Data
    public int Dimension;
    public float slotMargin;
    public float hintRadius;
    public Color hintColor;

    public PlayerType Player1;
    public PlayerType Player2;
    public Color P1_color;
    public Color P2_color;

    public Vector2 screenCenter = new Vector2(_virtualWidth/2f, _virtualHeight/2f);

    //set singleton
    private static final GameData _myInstance = new GameData();
    public static GameData getInstance(){
        return _myInstance;
    }
    private GameData(){}
}

