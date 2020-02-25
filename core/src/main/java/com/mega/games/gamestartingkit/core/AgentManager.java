package com.mega.games.gamestartingkit.core;

import java.util.ArrayList;

public class AgentManager {

    private int[][] gameState;

    public AgentManager(int[][] initialState) {
        gameState = initialState;
    }

    public<T> T play(int[][] currState, ArrayList<T> possibleMoves) {
        
        gameState = currState;
        return possibleMoves.get(0);
    }
}
