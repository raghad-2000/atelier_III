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

    public void buyCard(String appUserId, String cardId) {
        Optional<CardAssociation> cardAssociation = findCardAssociation(Long.parseLong(appUserId), Long.parseLong(cardId));
        // check if already existing
        if (cardAssociation.isPresent()) {
            cardAssociation.get().setQuantity(cardAssociation.get().getQuantity() + 1);
            cardAppUserRepository.save(cardAssociation.get());
        } else {
            CardAssociation ca = new CardAssociation(Long.parseLong(appUserId), Long.parseLong(cardId), 1);
            cardAppUserRepository.save(ca);
        }
    }
    public void sellCard(String appUserId, String cardId) {
        Optional<CardAssociation> cardAssociation = findCardAssociation(Long.parseLong(appUserId), Long.parseLong(cardId));
        if (cardAssociation.isPresent()) {
            CardAssociation cardAsso = cardAssociation.get();
            if (cardAsso.getQuantity() > 1) {
                cardAppUserRepository.save(new CardAssociation(cardAsso.getAppUserId(), cardAsso.getCardId(), cardAsso.getQuantity() - 1));
            } else {
                cardAppUserRepository.delete(cardAsso);
            }
        }

    }
    public Optional<CardAssociation> findCardAssociation(long appUserId, long cardId) {
        return cardAppUserRepository.findByAppUserIdAndCardId(appUserId, cardId);
    }
}
