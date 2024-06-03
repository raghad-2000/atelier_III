package com.transaction.service.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardAssociationDto {

    private long cardAssociationId;

    private long appUserId;
    private long cardId;
    private int quantity;
}

