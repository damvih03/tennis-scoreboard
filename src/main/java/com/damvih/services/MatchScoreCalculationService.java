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

            // Start tiebreak
            if (setScore.isTiebreak() && gameScore instanceof RegularGameScore) {
                fullScore.setGameScore(new TieBreakGameScore());
            }
            return ScoreState.CONTINUE;
        }

        setScore.reset();
        return matchScore.winPoint(playerNumber);
    }

    public boolean isMatchOverAfterWinningPoint(FullScore fullScore, int playerNumber) {
        return winPoint(fullScore, playerNumber) != ScoreState.CONTINUE;
    }

    public void setWinner(CurrentMatch currentMatch, int playerNumber) {
        String winner = playerNumber == 0 ? currentMatch.getPlayerOne() : currentMatch.getPlayerTwo();
        currentMatch.setWinner(winner);
    }

}
