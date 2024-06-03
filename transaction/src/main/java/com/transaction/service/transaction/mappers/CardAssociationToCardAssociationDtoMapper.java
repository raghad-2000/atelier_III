package com.transaction.service.transaction.mappers;

import com.transaction.service.transaction.dtos.CardAssociationDto;
import com.transaction.service.transaction.entity.CardAssociation;

public interface CardAssociationToCardAssociationDtoMapper {
    CardAssociationDto cardAssociationToCardAssociationDto(CardAssociation cardAssociation);
}
