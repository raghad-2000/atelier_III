package com.transaction.service.transaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CardAssociation {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
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
