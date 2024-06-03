package com.transaction.service.transaction.services;

import com.transaction.service.transaction.dtos.CardAssociationDto;
import com.transaction.service.transaction.entity.CardAssociation;
import com.transaction.service.transaction.mappers.CardAssociationToCardAssociationDtoImpl;
import com.transaction.service.transaction.mappers.CardAssociationToCardAssociationDtoMapper;
import com.transaction.service.transaction.repositories.CardAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    CardAppUserRepository cardAppUserRepository;

    private final CardAssociationToCardAssociationDtoImpl cardAssociationToCardAssociationDtoImpl= new CardAssociationToCardAssociationDtoImpl();

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
    public List<CardAssociationDto> findCardAssociationByUserId(long appUserId) {
        Optional<List<CardAssociation>> cardAssociations = cardAppUserRepository.findByAppUserId(appUserId);
        List<CardAssociationDto> cardAssociationDtos = new ArrayList<>();
        for(CardAssociation cardAssociation : cardAssociations.get()) {
            cardAssociationDtos.add(cardAssociationToCardAssociationDtoImpl.cardAssociationToCardAssociationDto(cardAssociation));
        }
        return cardAssociationDtos;
    }
}
