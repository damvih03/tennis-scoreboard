package com.damvih.services.score;

public class TieBreakGameScore extends GameScore<Integer> {

    public static final int MIN_POINTS_TO_WIN = 7;
    public static final int MIN_DIFFERENCE_TO_WIN = 2;

    protected ScoreState checkState(int playerNumber) {
        int enemyPlayerNumber = getEnemyPlayer(playerNumber);

        int playerPoints = getPlayerPoints(playerNumber);
        int enemyPlayerPoints = getPlayerPoints(enemyPlayerNumber);
        int difference = playerPoints - enemyPlayerPoints;

        if (playerPoints >= MIN_POINTS_TO_WIN && difference >= MIN_DIFFERENCE_TO_WIN) {
            return determineWinner(playerNumber);
        }
        return ScoreState.CONTINUE;
    }

    @Override
    public String getView(int playerNumber) {
        return super.getPlayerPoints(playerNumber).toString();
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

}
