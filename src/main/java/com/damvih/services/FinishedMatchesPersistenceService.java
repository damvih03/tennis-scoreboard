package com.damvih.services;

import com.damvih.dao.MatchDao;
import com.damvih.dao.PlayerDao;
import com.damvih.dto.MatchesResponseDto;
import com.damvih.entities.Match;
import com.damvih.entities.Player;
import com.damvih.mappers.MatchMapper;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchDao matchDao = new MatchDao();
    private final PlayerDao playerDao = new PlayerDao();
    private static final MatchMapper matchMapper = MatchMapper.INSTANCE;

    public MatchesResponseDto get(int page, String playerName) {
        List<Match> matches = matchDao.findAll(page, playerName);
        int totalPages = matchDao.getTotalPages(playerName);
        return new MatchesResponseDto(
                matchMapper.toDto(matches),
                totalPages
        );
    }

    public void save(CurrentMatch currentMatch) {
        Player playerOne = findOrSave(currentMatch.getPlayerOne());
        Player playerTwo = findOrSave(currentMatch.getPlayerTwo());
        Match match = Match.builder()
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .winner(getWinner(playerOne, playerTwo, currentMatch.getWinner()))
                .build();
        matchDao.save(match);
    }

    public Player findOrSave(String playerName) {
        Player player = Player.builder()
                .name(playerName)
                .build();
        return playerDao.findByName(playerName).orElseGet(() -> playerDao.save(player));
    }

    private Player getWinner(Player playerOne, Player playerTwo, String winnerName) {
        String playerOneName = playerOne.getName();
        return winnerName.equals(playerOneName) ? playerOne : playerTwo;
    }

}
