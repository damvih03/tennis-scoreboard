package com.damvih.services.action_strategies;

import com.damvih.services.score.FullScore;
import com.damvih.services.score.ScoreState;

public interface ScoreLevelActionStrategy {

    void perform(FullScore fullScore);

    void reset(FullScore fullScore);

    ScoreState winPoint(FullScore fullScore, int playerNumber);

}
