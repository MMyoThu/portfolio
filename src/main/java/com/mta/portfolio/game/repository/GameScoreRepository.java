package com.mta.portfolio.game.repository;

import com.mta.portfolio.game.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

    @Query("SELECT gs FROM GameScore gs JOIN FETCH gs.game g WHERE g.gameCode = :gameCode ORDER BY gs.score DESC")
    List<GameScore> findByGameCodeOrderByScoreDesc(@Param("gameCode") String gameCode);
}
