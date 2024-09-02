package com.damvih.services.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetScoreTest {

    @Test
    public void testPointsWonWithoutTiebreak() {
        SetScore setScore = new SetScore();
        for (int i = 0; i < SetScore.GAMES_TO_WIN_BEFORE_TIEBREAK - 1; i++) {
            assertEquals(ScoreState.CONTINUE, setScore.winPoint(0));
        }
        assertEquals(ScoreState.PLAYER_ONE_WON, setScore.winPoint(0));
    }

    @Test
    public void testPointsWonWithMinDifference() {
        SetScore setScore = new SetScore();

        for (int i = 0; i < SetScore.GAMES_TO_WIN_BEFORE_TIEBREAK - SetScore.MIN_DIFFERENCE_TO_WIN; i++) {
            setScore.winPoint(0);
            assertEquals(ScoreState.CONTINUE, setScore.winPoint(1));
        }

        assertEquals(ScoreState.CONTINUE, setScore.winPoint(1));
        assertEquals(ScoreState.PLAYER_TWO_WON, setScore.winPoint(1));
    }

    @Test
    public void testPointWonInTiebreak() {
        SetScore setScore = new SetScore();

        for (int i = 0; i < SetScore.GAMES_TO_WIN_BEFORE_TIEBREAK; i++) {
            setScore.winPoint(0);
            setScore.winPoint(1);
        }

        assertEquals(ScoreState.PLAYER_TWO_WON, setScore.winPoint(1));
    }

}
