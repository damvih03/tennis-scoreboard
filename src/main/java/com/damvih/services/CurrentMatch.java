package com.damvih.services;

import com.damvih.services.score.FullScore;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class CurrentMatch {

    private UUID uuid;
    private String playerOne;
    private String playerTwo;
    private String winner;
    private FullScore fullScore;

}
