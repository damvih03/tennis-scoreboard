package com.damvih.services;

import com.damvih.dto.NewMatchRequestDto;
import com.damvih.services.score.FullScore;
import com.damvih.services.score.GameScore;
import com.damvih.services.score.MatchScore;
import com.damvih.services.score.SetScore;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    public UUID create(NewMatchRequestDto newMatchRequestDto) {
        UUID uuid = UUID.randomUUID();
        CurrentMatch currentMatch = CurrentMatch.builder()
                .uuid(uuid)
                .playerOne(newMatchRequestDto.getPlayerOne())
                .playerTwo(newMatchRequestDto.getPlayerTwo())
                .fullScore(new FullScore(
                        new MatchScore(),
                        new SetScore(),
                        new GameScore()
                ))
                .build();
        currentMatches.put(uuid, currentMatch);
        return uuid;
    }

    public Optional<CurrentMatch> getMatch(UUID uuid) {
        return Optional.of(currentMatches.get(uuid));
    }

    public void remove(UUID uuid) {
        currentMatches.remove(uuid);
    }

}
