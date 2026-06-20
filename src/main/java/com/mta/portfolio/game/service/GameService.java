package com.mta.portfolio.game.service;

import com.mta.portfolio.game.dto.GameScoreRequest;
import com.mta.portfolio.game.entity.Game;
import com.mta.portfolio.game.entity.GameScore;

import java.util.List;

public interface GameService {

    List<Game> getAllGames();

    List<GameScore> getLeaderboard(String gameCode);

    GameScore submitScore(GameScoreRequest request);

    long getTotalGamesPlayed();
}
