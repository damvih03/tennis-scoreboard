package com.damvih.services.score;

public interface ScoreLevel {

    ScoreState winPoint(int playerNumber);
    void reset();

}
