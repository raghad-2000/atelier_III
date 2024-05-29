package com.card.service.card.mappers.impl;

import com.card.service.card.dto.CardDTO;
import com.card.service.card.entities.Card;
import com.card.service.card.mappers.CardEntityToCardDTO;

public class CardEntityToCardDTOImpl implements CardEntityToCardDTO
{

    public CardEntityToCardDTOImpl() {

    }

    @Override
    public CardDTO cardEntityToCardDTO(Card card) {
        return new CardDTO(card.getId(),
                card.getName(),
                card.getDescription(),
                card.getAffinity(),
                card.getFamily(),
                card.getEnergy(),
                card.getHp(),
                card.getPrice(),
                card.getRarity(),
                card.getUrl(),
                card.getPalId());
    }
}
