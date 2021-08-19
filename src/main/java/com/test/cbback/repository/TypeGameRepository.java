package com.test.cbback.repository;

import com.test.cbback.model.TypeGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeGameRepository extends CrudRepository<TypeGame, Long> {
}
