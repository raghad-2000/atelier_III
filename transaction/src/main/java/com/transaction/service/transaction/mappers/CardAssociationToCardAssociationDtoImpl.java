package com.transaction.service.transaction.mappers;

import com.transaction.service.transaction.dtos.CardAssociationDto;
import com.transaction.service.transaction.entity.CardAssociation;

public class CardAssociationToCardAssociationDtoImpl implements CardAssociationToCardAssociationDtoMapper{
    @Override
    public CardAssociationDto cardAssociationToCardAssociationDto(CardAssociation cardAssociation) {
        CardAssociationDto cardAssociationDto = new CardAssociationDto();

        cardAssociationDto.setCardAssociationId(cardAssociation.getCardAssociationId());
        cardAssociationDto.setCardId(cardAssociation.getCardId());
        cardAssociationDto.setQuantity(cardAssociation.getQuantity());
        cardAssociationDto.setAppUserId(cardAssociation.getAppUserId());

        return cardAssociationDto;
    }
}
