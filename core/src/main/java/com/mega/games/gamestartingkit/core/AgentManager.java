package com.mega.games.gamestartingkit.core;

import com.mega.games.gamestartingkit.core.Agents.Dummy;
import com.mega.games.gamestartingkit.core.Agents.PlayerAgent;
import com.mega.games.gamestartingkit.core.Agents.SARSA;

import java.util.ArrayList;

public class AgentManager {

    private int[][] gameState;
    private Callbacks game_callbacks;

    private PlayerAgent agent;

    public AgentManager(int[][] initialState, String agentType,Callbacks callbacks) {
        gameState = initialState;
        game_callbacks = callbacks;

        switch (agentType.trim()) {
            case "Dummy": {
                agent = new Dummy();
                break;
            }
            case "SARSA":
                agent = new SARSA();
                break;
            default:
                throw new IllegalStateException("Unexpected agentType value: " + agentType);
        }
    }

    public<T> void play(int[][] currState, ArrayList<T> possibleMoves) {

        gameState = currState;
        T myMove = agent.getBestMove(possibleMoves);

        int reward = game_callbacks.executeMoveGetReward(myMove);
        System.out.println("reward:" + reward);
    }



    public interface Callbacks {
        <T> int executeMoveGetReward(T next_move);
    }

}
