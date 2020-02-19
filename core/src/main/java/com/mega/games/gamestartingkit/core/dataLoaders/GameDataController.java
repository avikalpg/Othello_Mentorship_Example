package com.mega.games.gamestartingkit.core.dataLoaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mega.games.support.MegaServices;

import java.util.HashMap;
import java.util.Map;

public class GameDataController{
    private boolean isConfigValid;
    private String configError;
    private MegaServices megaServices;
    private GameData data = GameData.getInstance();

    void setMegaServices(MegaServices megaServ){
        megaServices = megaServ;

        isConfigValid = validateConfig();
        if(!isConfigValid){
            HashMap<String,Object> rv = new HashMap<>();
            rv.put("error",configError);
            megaServ.analytics().logEvent("Invalid_Config_Found", rv);
        }
    }

    public void startGame(){
        data.isGameEnded = false;
        data.isGameOver = false;
        GameInfra.getInstance().megaServices.callbacks().playStarted();
    }

    private boolean validateConfig(){
        try{
            //Todo: Add Checks here
            //Example:
            float maxZomSp = Float.parseFloat((String)megaServices.config().get("maxZombieSpeed"));
            float minZomSp = Float.parseFloat((String)megaServices.config().get("minZombieSpeed"));
            if(maxZomSp < minZomSp || maxZomSp < 0 || minZomSp < 0){
                throw new Exception("malformed zombie speed bounds");
            }
        }catch (Exception e){
            configError = e.getMessage();
            return false;
        }

        return true;
    }

    private String extractValFromConfig(String key, String defVal){
        if(isConfigValid){
            return (String)megaServices.config().get(key);
        }else{
            return defVal;
        }
    }

    public void setDefault(){
        data.elapsed = 0;
        data.black_score = 2;
        data.white_score = 2;

        data.gameEndLag = 1;

        //Get Data From Config
        //Todo: setDefault config data etc
        data.Dimension = 8;
        data.slotMargin = 1f;
        data.hintRadius = 5f;
        data.hintColor = Color.SKY;
        data.backgroundColor = Color.FIREBRICK;

        data.Player1 = PlayerType.HUMAN;
        data.Player2 = PlayerType.AI;

        data.P1_color = Color.BLACK;
        data.P2_color = Color.WHITE;
    }

    public void setGameEnded(){
        if(!data.isGameEnded) {
            data.isGameEnded = true;
            Gdx.input.setInputProcessor(null);
            megaServices.analytics().logEvent("Death", getGameState());
        }
    }

    public boolean getIsGameEnded(){
        return data.isGameEnded;
    }

    private Map<String,Object> getGameState(){
        Map<String,Object> rv = new HashMap<>();

        //Todo: Fill the keys
        //keys shouldn't have spaces

        return rv;
    }

    public void setGameOver(){
        if(!data.isGameOver) {
            data.isGameEnded = true;
            data.isGameOver = true;
            megaServices.callbacks().gameOver((long)data.black_score - (long)data.white_score);
        }
    }

    public boolean getIsGameOver(){
        return data.isGameOver;
    }

    public void update(float dt){
        data.elapsed += dt;
    }

    //set singleton
    private static final GameDataController _myInstance = new GameDataController();
    public static GameDataController getInstance(){
        return _myInstance;
    }
    private GameDataController(){}
}
