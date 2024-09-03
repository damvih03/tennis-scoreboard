package com.damvih.services.action_strategies;

import com.damvih.services.score.FullScore;
import com.damvih.services.score.GameScore;
import com.damvih.services.score.RegularGameScore;
import com.damvih.services.score.ScoreState;

public class GameScoreActionStrategy implements ScoreLevelActionStrategy {

    @Override
    public void perform(FullScore fullScore) {

    }

    @Override
    public void reset(FullScore fullScore) {
        fullScore.setGameScore(new RegularGameScore());
    }

    @Override
    public ScoreState winPoint(FullScore fullScore, int playerNumber) {
        GameScore<?> gameScore = fullScore.getGameScore();
        return gameScore.winPoint(playerNumber);
    }

}
