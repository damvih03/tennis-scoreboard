package com.damvih.services.score;

public class TieBreakGameScore extends IntegerScore implements GameScore {

    public static final int MIN_POINTS_TO_WIN = 7;
    public static final int MIN_DIFFERENCE_TO_WIN = 2;

    @Override
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

}
