package com.damvih.services;

import com.damvih.services.score.*;

public class MatchScoreCalculationService {

    // TODO: Refactor
    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        GameScore<?> gameScore = fullScore.getGameScore();
        SetScore setScore = fullScore.getSetScore();
        MatchScore matchScore = fullScore.getMatchScore();


        if (gameScore.winPoint(playerNumber) != ScoreState.CONTINUE) {
            gameScore.reset();

            if (setScore.winPoint(playerNumber) == ScoreState.CONTINUE) {
                if (setScore.isTiebreak() && gameScore instanceof RegularGameScore) {
                    fullScore.setGameScore(new TieBreakGameScore());
                }
                return ScoreState.CONTINUE;
            }

            setScore.reset();
            fullScore.setGameScore(new RegularGameScore());
            return matchScore.winPoint(playerNumber);

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
