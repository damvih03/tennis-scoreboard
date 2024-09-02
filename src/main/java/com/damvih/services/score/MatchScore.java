package com.damvih.services.score;

public class MatchScore extends Score<Integer> {

    public static final int SETS_TO_WIN = 2;

    @Override
    public void reset() {
        setPlayerPoints(PLAYER_ONE, 0);
        setPlayerPoints(PLAYER_TWO, 0);
    }

    @Override
    public ScoreState winPoint(int playerNumber) {
        setPlayerPoints(playerNumber, getPlayerPoints(playerNumber) + 1);
        return checkState(playerNumber);
    }

    protected ScoreState checkState(int playerNumber) {
        if (getPlayerPoints(playerNumber) == SETS_TO_WIN) {
            return determineWinner(playerNumber);
        }
        return ScoreState.CONTINUE;
    }

}
