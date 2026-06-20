package com.mta.portfolio.game.repository;

import com.mta.portfolio.game.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
    @Query("SELECT gs FROM GameScore gs WHERE gs.game.gameCode = :gameCode ORDER BY gs.score DESC")
    List<GameScore> findTopByGameCodeOrderByScoreDesc(String gameCode);

    long count();
}
