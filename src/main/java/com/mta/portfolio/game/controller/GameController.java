package com.mta.portfolio.game.controller;

import com.mta.portfolio.common.response.ApiResponse;
import com.mta.portfolio.game.dto.GameScoreRequest;
import com.mta.portfolio.game.entity.Game;
import com.mta.portfolio.game.entity.GameScore;
import com.mta.portfolio.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Game>>> getGames() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", gameService.getAllGames()));
    }

    @GetMapping("/leaderboard/{gameCode}")
    public ResponseEntity<ApiResponse<List<GameScore>>> getLeaderboard(@PathVariable String gameCode) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", gameService.getLeaderboard(gameCode)));
    }

    @PostMapping("/score")
    public ResponseEntity<ApiResponse<GameScore>> submitScore(@Valid @RequestBody GameScoreRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", gameService.submitScore(request)));
    }
}
