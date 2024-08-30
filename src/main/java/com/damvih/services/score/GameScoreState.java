package com.damvih.services.score;

import lombok.Getter;

@Getter
public enum GameScoreState {

    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String view;

    GameScoreState(String view) {
        this.view = view;
    }

    public static GameScoreState nextValue(int index) {
        return GameScoreState.values()[index + 1];
    }

}
