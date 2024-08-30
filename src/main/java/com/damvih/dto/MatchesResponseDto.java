package com.damvih.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchesResponseDto {

    private List<MatchResponseDto> matches;
    private int totalPages;

}
