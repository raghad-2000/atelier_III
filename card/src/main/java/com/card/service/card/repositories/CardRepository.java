package com.card.service.card.repositories;

import com.card.service.card.entities.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long>
{

    List<Card> findByName(String name);

    @Query(value = "SELECT * FROM card", nativeQuery = true)
    List<Card> findAll();
}
