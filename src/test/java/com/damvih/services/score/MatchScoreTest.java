package com.damvih.services.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreTest {

    @Test
    public void testPointsWon() {
        MatchScore matchScore = new MatchScore();

        assertEquals(ScoreState.CONTINUE, matchScore.winPoint(0));
        assertEquals(ScoreState.PLAYER_ONE_WON, matchScore.winPoint(0));
    }

}
