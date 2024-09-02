package com.damvih.services.score;

public class SetScore extends Score<Integer> {

    public static final int GAMES_TO_WIN_BEFORE_TIEBREAK = 6;
    public static final int MIN_DIFFERENCE_TO_WIN = 2;

    protected ScoreState checkState(int playerNumber) {
        int enemyPlayerNumber = getEnemyPlayer(playerNumber);

        int playerPoints = getPlayerPoints(playerNumber);
        int enemyPlayerPoints = getPlayerPoints(enemyPlayerNumber);
        int difference = playerPoints - enemyPlayerPoints;

        if (playerPoints >= GAMES_TO_WIN_BEFORE_TIEBREAK && difference >= MIN_DIFFERENCE_TO_WIN) {
            return determineWinner(playerNumber);
        }
        return ScoreState.CONTINUE;
    }

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

    public boolean isTiebreak() {
        int playerOnePoints = getPlayerPoints(PLAYER_ONE);
        int playerTwoPoints = getPlayerPoints(PLAYER_TWO);
        return playerOnePoints == GAMES_TO_WIN_BEFORE_TIEBREAK && playerTwoPoints == GAMES_TO_WIN_BEFORE_TIEBREAK;
    }

}
