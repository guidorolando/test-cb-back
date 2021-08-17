package com.test.cbback.repository;

import com.test.cbback.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
}
