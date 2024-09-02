package com.damvih.services.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullScore {

    private MatchScore matchScore;
    private SetScore setScore;
    private GameScore<?> gameScore;

}
