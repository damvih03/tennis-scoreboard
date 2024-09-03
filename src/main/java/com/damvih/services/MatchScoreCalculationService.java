package com.damvih.services;

import com.damvih.services.score.*;

public class MatchScoreCalculationService {

    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        GameScore<?> gameScore = fullScore.getGameScore();
        SetScore setScore = fullScore.getSetScore();
        MatchScore matchScore = fullScore.getMatchScore();

        if (gameScore.winPoint(playerNumber) == ScoreState.CONTINUE) {
            return ScoreState.CONTINUE;
        }
        fullScore.setGameScore(new RegularGameScore());

        if (setScore.winPoint(playerNumber) == ScoreState.CONTINUE) {
            startTiebreak(fullScore);
            return ScoreState.CONTINUE;
        }

        fullScore.setSetScore(new SetScore());
        return matchScore.winPoint(playerNumber);
    }

    public boolean isMatchOverAfterWinningPoint(FullScore fullScore, int playerNumber) {
        return winPoint(fullScore, playerNumber) != ScoreState.CONTINUE;
    }

    public void setWinner(CurrentMatch currentMatch, int playerNumber) {
        String winner = playerNumber == 0 ? currentMatch.getPlayerOne() : currentMatch.getPlayerTwo();
        currentMatch.setWinner(winner);
    }

    private void startTiebreak(FullScore fullScore) {
        if (fullScore.getSetScore().isTie() && fullScore.getGameScore() instanceof RegularGameScore) {
            fullScore.setGameScore(new TieBreakGameScore());
        }
    }

}
