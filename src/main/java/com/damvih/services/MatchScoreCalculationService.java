package com.damvih.services;

import com.damvih.services.score.FullScore;
import com.damvih.services.score.ScoreLevel;
import com.damvih.services.score.ScoreState;

import java.util.List;

public class MatchScoreCalculationService {

    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        List<ScoreLevel> scoreLevels = List.of(
                fullScore.getGameScore(),
                fullScore.getSetScore(),
                fullScore.getMatchScore()
        );

        ScoreState state = ScoreState.CONTINUE;
        for (ScoreLevel level : scoreLevels) {
            state = level.winPoint(playerNumber);
            if (state == ScoreState.CONTINUE) {
                return state;
            }
            level.reset();
        }
        return state;
    }

    public boolean isMatchOverAfterWinningPoint(FullScore fullScore, int playerNumber) {
        return winPoint(fullScore, playerNumber) != ScoreState.CONTINUE;
    }

    public void setWinner(CurrentMatch currentMatch, int playerNumber) {
        String winner = playerNumber == 0 ? currentMatch.getPlayerOne() : currentMatch.getPlayerTwo();
        currentMatch.setWinner(winner);
    }

}
