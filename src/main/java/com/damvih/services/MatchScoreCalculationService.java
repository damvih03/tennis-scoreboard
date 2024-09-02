package com.damvih.services;

import com.damvih.services.score.*;

public class MatchScoreCalculationService {

    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        MatchScore matchScore = fullScore.getMatchScore();
        SetScore setScore = fullScore.getSetScore();
        GameScore gameScore = fullScore.getGameScore();

        ScoreState state = gameScore.winPoint(playerNumber);
        if (state != ScoreState.CONTINUE) {
            gameScore.reset();

            state = setScore.winPoint(playerNumber);
            if (state != ScoreState.CONTINUE) {
                setScore.reset();
                return matchScore.winPoint(playerNumber);
            }

            if (setScore.isTiebreak() && gameScore instanceof RegularGameScore) {
                fullScore.setGameScore(new TieBreakGameScore());
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
