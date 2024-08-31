package com.damvih.services.score;

import java.util.ArrayList;
import java.util.List;

abstract public class Score<T> implements ScoreLevel {

    private final List<T> playerPoints = new ArrayList<>();
    protected static final int PLAYER_ONE = 0;
    protected static final int PLAYER_TWO = 1;

    public Score() {
        playerPoints.add(null);
        playerPoints.add(null);
        reset();
    }

    public int getEnemyPlayer(int playerNumber) {
        return playerNumber == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
    }

    public T getPlayerPoints(int playerNumber) {
        return playerPoints.get(playerNumber);
    }

    public void setPlayerPoints(int playerNumber, T point) {
        playerPoints.set(playerNumber, point);
    }

    protected static ScoreState determineWinner(int playerNumber) {
        return playerNumber == PLAYER_ONE ? ScoreState.PLAYER_ONE_WON : ScoreState.PLAYER_TWO_WON;
    }

}
