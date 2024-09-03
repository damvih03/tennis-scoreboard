package com.damvih.services.score;

import lombok.Getter;

@Getter
public enum RegularGameScoreState {

    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String value;

    RegularGameScoreState(String value) {
        this.value = value;
    }

    public static RegularGameScoreState nextValue(int index) {
        return RegularGameScoreState.values()[index + 1];
    }

}
