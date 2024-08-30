package com.damvih.services.score;

public class MatchScore extends RegularScore {

    public static final int SETS_TO_WIN = 2;

    @Override
    protected ScoreState checkState(int playerNumber) {
        if (getPlayerPoints(playerNumber) == SETS_TO_WIN) {
            return determineWinner(playerNumber);
        }
        return ScoreState.CONTINUE;
    }

}
