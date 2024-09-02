package com.damvih.services;

import com.damvih.services.score.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreCalculationServiceTest {

    @Test
    public void testFullScore() {
        FullScore fullScore = new FullScore(
                new MatchScore(),
                new SetScore(),
                new RegularGameScore()
        );
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        for (int i = 0; i < 47; i++) {
            assertEquals(ScoreState.CONTINUE, matchScoreCalculationService.winPoint(fullScore, 0));
        }

        assertEquals(ScoreState.PLAYER_ONE_WON, matchScoreCalculationService.winPoint(fullScore, 0));
    }

}
