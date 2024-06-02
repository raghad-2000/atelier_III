package com.transaction.service.transaction.services;

import com.transaction.service.transaction.entity.CardAssociation;
import com.transaction.service.transaction.repositories.CardAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    CardAppUserRepository cardAppUserRepository;

    public void buyCard(Long appUserId, Long cardId) {
        Optional<CardAssociation> cardAssociation = findCardAssociation(appUserId, cardId);
        // check if already existing
        if (cardAssociation.isPresent()) {
            cardAssociation.get().setQuantity(cardAssociation.get().getQuantity() + 1);
            cardAppUserRepository.save(cardAssociation.get());
        } else {
            CardAssociation ca = new CardAssociation(appUserId, cardId, 1);
            cardAppUserRepository.save(ca);
        }
    }
    public void sellCard(Long appUserId, Long cardId) {
        Optional<CardAssociation> cardAssociation = findCardAssociation(appUserId, cardId);
        if (cardAssociation.isPresent()) {
            if (cardAssociation.get().getQuantity() > 1) {
                cardAssociation.get().setQuantity(cardAssociation.get().getQuantity() - 1);
                cardAppUserRepository.save(cardAssociation.get());
            } else {
                cardAppUserRepository.delete(cardAssociation.get());
            }
        }

    }
    public Optional<CardAssociation> findCardAssociation(long appUserId, long cardId) {
        return cardAppUserRepository.findByAppUserIdAndCardId(appUserId, cardId);
    }
}
