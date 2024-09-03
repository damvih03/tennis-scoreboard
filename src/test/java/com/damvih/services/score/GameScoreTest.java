package com.damvih.services.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameScoreTest {

    @Test
    public void testPointsWonWithoutAdvantage() {
        RegularGameScore gameScore = new RegularGameScore();
        for (int i = 0; i < 3; i++) {
            assertEquals(gameScore.winPoint(0), ScoreState.CONTINUE);
        }
        assertEquals(gameScore.winPoint(0), ScoreState.PLAYER_ONE_WON);
    }

    @Test
    public void testAdvantage() {
        RegularGameScore gameScore = new RegularGameScore();

        for (int i = 0; i < 3; i++) {
            gameScore.winPoint(0);
            gameScore.winPoint(1);
        }

        // AD:40
        assertEquals(gameScore.winPoint(0), ScoreState.CONTINUE);
        assertEquals(gameScore.getPlayerPoints(0), RegularGameScoreState.ADVANTAGE);

        // 40:40
        assertEquals(gameScore.winPoint(1), ScoreState.CONTINUE);
        assertEquals(gameScore.getPlayerPoints(0), RegularGameScoreState.FORTY);
        assertEquals(gameScore.getPlayerPoints(1), RegularGameScoreState.FORTY);

        gameScore.winPoint(0);
        assertEquals(gameScore.winPoint(0), ScoreState.PLAYER_ONE_WON);
    }

}
