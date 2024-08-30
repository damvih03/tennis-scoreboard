package com.damvih.services.score;

abstract public class RegularScore extends Score<Integer> {

    abstract protected ScoreState checkState(int playerName);

    @Override
    public void reset() {
        setPlayerPoints(PLAYER_ONE, 0);
        setPlayerPoints(PLAYER_TWO, 0);
    }

    public ScoreState winPoint(int playerNumber) {
        setPlayerPoints(playerNumber, getPlayerPoints(playerNumber) + 1);
        return checkState(playerNumber);
    }

}
