package com.damvih.services.score;

import lombok.Getter;

@Getter
public enum GameScoreState {

    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String value;

    GameScoreState(String value) {
        this.value = value;
    }

    public static GameScoreState nextValue(int index) {
        return GameScoreState.values()[index + 1];
    }

}
