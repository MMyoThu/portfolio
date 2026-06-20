package com.mta.portfolio.game.service.impl;

import com.mta.portfolio.common.exception.ResourceNotFoundException;
import com.mta.portfolio.game.dto.GameScoreRequest;
import com.mta.portfolio.game.entity.Game;
import com.mta.portfolio.game.entity.GameScore;
import com.mta.portfolio.game.repository.GameRepository;
import com.mta.portfolio.game.repository.GameScoreRepository;
import com.mta.portfolio.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameScoreRepository gameScoreRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<GameScore> getLeaderboard(String gameCode) {
        return gameScoreRepository.findTopByGameCodeOrderByScoreDesc(gameCode);
    }

    @Override
    public GameScore submitScore(GameScoreRequest request) {
        Game game = gameRepository.findByGameCode(request.getGameCode())
                .orElseThrow(() -> new ResourceNotFoundException("Game", "gameCode", request.getGameCode()));

        GameScore score = new GameScore();
        score.setGame(game);
        score.setPlayerName(request.getPlayerName());
        score.setScore(request.getScore());
        return gameScoreRepository.save(score);
    }

    @Override
    public long getTotalGamesPlayed() {
        return gameScoreRepository.count();
    }
}
