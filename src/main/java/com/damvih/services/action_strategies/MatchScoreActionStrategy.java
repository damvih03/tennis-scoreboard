package com.damvih.services.action_strategies;

import com.damvih.services.score.FullScore;
import com.damvih.services.score.MatchScore;
import com.damvih.services.score.ScoreState;

public class MatchScoreActionStrategy implements ScoreLevelActionStrategy {

    @Override
    public void perform(FullScore fullScore) {

    }

    @Override
    public void reset(FullScore fullScore) {
        fullScore.setMatchScore(new MatchScore());
    }

    @Override
    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        MatchScore matchScore = fullScore.getMatchScore();
        return matchScore.winPoint(playerNumber);
    }

}
