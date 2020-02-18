package com.mega.games.gamestartingkit.core.dataLoaders;

public class WorldData{

    public void setDefault(){
        //Rarely filled
    }

    //set singleton
    private static final WorldData _myInstance = new WorldData();
    public static WorldData getInstance(){
        return _myInstance;
    }
    private WorldData(){}
}
