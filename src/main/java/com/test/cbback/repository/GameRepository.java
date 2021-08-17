package com.test.cbback.repository;

import com.test.cbback.model.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByStatus(Boolean status);

    @Transactional
    @Modifying
    @Query(value = "update Game g set g.status = :status where g.status = 1 ")
    void updateGameStatus(@Param("status") Boolean status);
}
