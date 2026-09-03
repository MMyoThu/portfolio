package com.mta.portfolio.game.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameScoreRequest {
    @NotBlank
    private String gameCode;

    @NotBlank
    private String playerName;

    @NotNull
    @Min(0)
    private Integer score;
}
