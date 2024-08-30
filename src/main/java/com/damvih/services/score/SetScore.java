package com.damvih.services.score;

public class SetScore extends RegularScore {

    public static final int GAMES_TO_WIN_BEFORE_TIEBREAK = 6;
    public static final int MIN_DIFFERENCE_TO_WIN = 2;

    @Override
    protected ScoreState checkState(int playerNumber) {
        int enemyPlayerNumber = getEnemyPlayer(playerNumber);

        int playerPoints = getPlayerPoints(playerNumber);
        int enemyPlayerPoints = getPlayerPoints(enemyPlayerNumber);
        int difference = playerPoints - enemyPlayerPoints;

        if (playerPoints == GAMES_TO_WIN_BEFORE_TIEBREAK && enemyPlayerPoints <= GAMES_TO_WIN_BEFORE_TIEBREAK - MIN_DIFFERENCE_TO_WIN) {
            return determineWinner(playerNumber);
        } else if (playerPoints > GAMES_TO_WIN_BEFORE_TIEBREAK && difference == MIN_DIFFERENCE_TO_WIN) {
            return determineWinner(playerNumber);
        }
        return ScoreState.CONTINUE;
    }

}
