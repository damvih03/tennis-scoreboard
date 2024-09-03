package com.damvih.services.action_strategies;

import com.damvih.services.score.*;

public class SetScoreActionStrategy implements ScoreLevelActionStrategy {

    @Override
    public void perform(FullScore fullScore) {
        if (isTiebreakStarted(fullScore)) {
            fullScore.setGameScore(new TieBreakGameScore());
        }
    }

    @Override
    public void reset(FullScore fullScore) {
        fullScore.setSetScore(new SetScore());
    }

    @Override
    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        SetScore setScore = fullScore.getSetScore();
        return setScore.winPoint(playerNumber);
    }

    private boolean isTiebreakStarted(FullScore fullScore) {
        return fullScore.getSetScore().isTie() && fullScore.getGameScore() instanceof RegularGameScore;
    }

}
