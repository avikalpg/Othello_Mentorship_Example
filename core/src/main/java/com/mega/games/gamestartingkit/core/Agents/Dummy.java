package com.mega.games.gamestartingkit.core.Agents;

import java.util.ArrayList;

public class Dummy extends PlayerAgent {
    @Override
    public <T> T getBestMove(ArrayList<T> possibleMoves) {
        return possibleMoves.get(0);
    }

}
