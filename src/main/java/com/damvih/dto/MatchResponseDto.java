package com.damvih.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto {

    private PlayerResponseDto playerOne;
    private PlayerResponseDto playerTwo;
    private PlayerResponseDto winner;

}
