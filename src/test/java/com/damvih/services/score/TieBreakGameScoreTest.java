package com.damvih.services.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieBreakGameScoreTest {

    @Test
    public void testPointsWonWithoutMinDifference() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();
        for (int i = 1; i < TieBreakGameScore.MIN_POINTS_TO_WIN; i++) {
            assertEquals(ScoreState.CONTINUE, tieBreakGameScore.winPoint(Score.PLAYER_ONE));
        }
        assertEquals(ScoreState.PLAYER_ONE_WON, tieBreakGameScore.winPoint(Score.PLAYER_ONE));
    }

    @Test
    public void testPointsWonWithMinDifference() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();
        for (int i = 0; i < TieBreakGameScore.MIN_POINTS_TO_WIN; i++) {
            tieBreakGameScore.winPoint(Score.PLAYER_ONE);
            tieBreakGameScore.winPoint(Score.PLAYER_TWO);
        }
        assertEquals(ScoreState.CONTINUE, tieBreakGameScore.winPoint(Score.PLAYER_ONE));
        assertEquals(ScoreState.PLAYER_ONE_WON, tieBreakGameScore.winPoint(Score.PLAYER_ONE));
    }

}
