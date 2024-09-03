package com.damvih.services;

import com.damvih.services.action_strategies.GameScoreActionStrategy;
import com.damvih.services.action_strategies.MatchScoreActionStrategy;
import com.damvih.services.action_strategies.ScoreLevelActionStrategy;
import com.damvih.services.action_strategies.SetScoreActionStrategy;
import com.damvih.services.score.*;

import java.util.List;

public class MatchScoreCalculationService {

    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        List<ScoreLevelActionStrategy> strategies = List.of(
                new GameScoreActionStrategy(),
                new SetScoreActionStrategy(),
                new MatchScoreActionStrategy()
        );

        ScoreState state = null;
        for (ScoreLevelActionStrategy strategy: strategies) {
            state = strategy.winPoint(fullScore, playerNumber);
            if (state == ScoreState.CONTINUE) {
                strategy.perform(fullScore);
                return ScoreState.CONTINUE;
            }
            strategy.reset(fullScore);
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
