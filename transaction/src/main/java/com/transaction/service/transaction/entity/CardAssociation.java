package com.transaction.service.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CardAssociation {

    @Id
    private long cardAssociationId;

    private long appUserId;
    private long cardId;
    private int quantity;
    public CardAssociation() {

    }

    public CardAssociation(long appUserId, long cardId, int quantity) {
        this.appUserId = appUserId;
        this.cardId = cardId;
        this.quantity = quantity;
    }

}
