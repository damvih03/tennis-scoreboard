package com.damvih.services.score;

public class RegularGameScore extends GameScore<RegularGameScoreState> {

    @Override
    public String getView(int playerNumber) {
        return super.getPlayerPoints(playerNumber).getValue();
    }

    @Override
    public void reset() {
        setPlayerPoints(PLAYER_ONE, RegularGameScoreState.ZERO);
        setPlayerPoints(PLAYER_TWO, RegularGameScoreState.ZERO);
    }

    @Override
    public ScoreState winPoint(int playerNumber) {
        int enemyPlayerNumber = getEnemyPlayer(playerNumber);

        int playerPoints = getPlayerPoints(playerNumber).ordinal();
        int enemyPlayerPoints = getPlayerPoints(enemyPlayerNumber).ordinal();

        if (isGameOver(playerPoints, enemyPlayerPoints)) {
            return determineWinner(playerNumber);
        } else if (playerPoints < RegularGameScoreState.FORTY.ordinal() || enemyPlayerPoints == RegularGameScoreState.FORTY.ordinal()) {
            setPlayerPoints(playerNumber, RegularGameScoreState.nextValue(playerPoints));
        } else if (enemyPlayerPoints == RegularGameScoreState.ADVANTAGE.ordinal()) {
            setPlayerPoints(enemyPlayerNumber, RegularGameScoreState.FORTY);
        }
        return ScoreState.CONTINUE;
    }

    private boolean isGameOver(int playerPoints, int enemyPlayerPoints) {
        return playerPoints == RegularGameScoreState.FORTY.ordinal() && enemyPlayerPoints < RegularGameScoreState.FORTY.ordinal() || playerPoints == RegularGameScoreState.ADVANTAGE.ordinal();
    }

}
