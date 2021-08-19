package com.test.cbback.repository;

import com.test.cbback.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {

    List<Bet> findByGameId(Long gameId);
    Bet findByGameIdAndUserId(Long gameId, Long usersId);
    List<Bet> findByGameIdAndWinner(Long gameId, Boolean winner);
}
