package com.transaction.service.transaction.repositories;

import com.transaction.service.transaction.entity.CardAssociation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardAppUserRepository extends CrudRepository<CardAssociation, Integer> {

    Optional<CardAssociation> findByAppUserIdAndCardId(long appUserId, long cardId);

    Optional<List<CardAssociation>> findByAppUserId(long appUserId);
}
