package com.mega.games.gamestartingkit.core.dataLoaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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
        data.score = 0;

        data.gameEndLag = 5;

        //Get Data From Config
        //Todo: setDefault config data etc
        //Example 1: Bounded var increasing with time
        float minZombieSpeed = Float.parseFloat(extractValFromConfig("minZombieSpeed", "1"));
        float maxZombieSpeed = Float.parseFloat(extractValFromConfig("maxZombieSpeed", "2.5"));
        data.zombieSpeedBounds = new Vector2(minZombieSpeed,maxZombieSpeed);
        data.zombieSpeed = minZombieSpeed;

        //Example 2: Random var within some range
        float minZombieScale = Float.parseFloat(extractValFromConfig("minZombieScale", "1"));
        float maxZombieScale = Float.parseFloat(extractValFromConfig("maxZombieScale", "2"));
        data.zombieScaleBounds = new Vector2(minZombieScale,maxZombieScale);
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
            megaServices.callbacks().gameOver((long)data.score);
        }
    }

    public boolean getIsGameOver(){
        return data.isGameOver;
    }

    public void update(float dt){
        data.elapsed += dt;
        float factor = (float)Math.pow(0.997, data.elapsed);

        //update game vars with time
        //Example 1: Bounded var increasing with time
        data.zombieSpeed = factor*data.zombieSpeedBounds.x + (1-factor)*data.zombieSpeedBounds.y;
    }

    //set singleton
    private static final GameDataController _myInstance = new GameDataController();
    public static GameDataController getInstance(){
        return _myInstance;
    }
    private GameDataController(){}
}
