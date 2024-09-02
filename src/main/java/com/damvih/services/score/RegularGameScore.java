package com.damvih.services.score;

public class RegularGameScore extends Score<GameScoreState> {

    @Override
    public void reset() {
        setPlayerPoints(PLAYER_ONE, GameScoreState.ZERO);
        setPlayerPoints(PLAYER_TWO, GameScoreState.ZERO);
    }

    @Override
    public ScoreState winPoint(int playerNumber) {
        int enemyPlayerNumber = getEnemyPlayer(playerNumber);

        int playerPoints = getPlayerPoints(playerNumber).ordinal();
        int enemyPlayerPoints = getPlayerPoints(enemyPlayerNumber).ordinal();

        if (isGameOver(playerPoints, enemyPlayerPoints)) {
            return determineWinner(playerNumber);
        } else if (playerPoints < GameScoreState.FORTY.ordinal() || enemyPlayerPoints == GameScoreState.FORTY.ordinal()) {
            setPlayerPoints(playerNumber, GameScoreState.nextValue(playerPoints));
        } else if (enemyPlayerPoints == GameScoreState.ADVANTAGE.ordinal()) {
            setPlayerPoints(enemyPlayerNumber, GameScoreState.FORTY);
        }
        return ScoreState.CONTINUE;
    }

    private boolean isGameOver(int playerPoints, int enemyPlayerPoints) {
        return playerPoints == GameScoreState.FORTY.ordinal() && enemyPlayerPoints < GameScoreState.FORTY.ordinal() || playerPoints == GameScoreState.ADVANTAGE.ordinal();
    }

}
