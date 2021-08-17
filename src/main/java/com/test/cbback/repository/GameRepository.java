package com.test.cbback.repository;

import com.test.cbback.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByStatus(Boolean status);
}
