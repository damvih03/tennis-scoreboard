package com.damvih.utils;

import com.damvih.dto.NewMatchRequestDto;

public class ValidationUtils {

    private static final String PLAYER_NAME_PATTERN = "^[A-Z][a-zA-Z]{2,15}$";

    private ValidationUtils() {

    }

    public static boolean isParameterMissed(String parameterValue) {
        return parameterValue == null || parameterValue.isBlank();
    }

    public static void validatePlayerNumber(int playerNumber) {
        if (playerNumber < 0 || playerNumber > 1) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNewMatch(NewMatchRequestDto newMatchRequestDto) {
        String playerOneName = newMatchRequestDto.getPlayerOne();
        String playerTwoName = newMatchRequestDto.getPlayerTwo();

        if (playerOneName.equals(playerTwoName)) {
            throw new IllegalArgumentException();
        }

        validatePlayerName(playerOneName);
        validatePlayerName(playerTwoName);
    }

    private static void validateParameterValue(String parameterValue) {
        if (isParameterMissed(parameterValue)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validatePlayerName(String playerName) {
        validateParameterValue(playerName);
        if (!playerName.matches(PLAYER_NAME_PATTERN)) {
            throw new IllegalArgumentException();
        }
    }

}
