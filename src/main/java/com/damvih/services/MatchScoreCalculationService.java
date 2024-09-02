package com.damvih.services;

import com.damvih.services.score.*;

public class MatchScoreCalculationService {

    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        MatchScore matchScore = fullScore.getMatchScore();
        SetScore setScore = fullScore.getSetScore();
        RegularGameScore gameScore = fullScore.getGameScore();

        if (gameScore.winPoint(playerNumber) != ScoreState.CONTINUE) {
            gameScore.reset();
            if (setScore.winPoint(playerNumber) != ScoreState.CONTINUE) {
                setScore.reset();
                return matchScore.winPoint(playerNumber);
            }
        }
        return ScoreState.CONTINUE;
    }

    public boolean isMatchOverAfterWinningPoint(FullScore fullScore, int playerNumber) {
        return winPoint(fullScore, playerNumber) != ScoreState.CONTINUE;
    }

    public void setWinner(CurrentMatch currentMatch, int playerNumber) {
        String winner = playerNumber == 0 ? currentMatch.getPlayerOne() : currentMatch.getPlayerTwo();
        currentMatch.setWinner(winner);
    }

}
