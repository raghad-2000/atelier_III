package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {
    // Setter pour userId
    // Getter pour userId
    private Long userId;
    // Setter pour cardId
    // Getter pour cardId
    private Long cardId;

    public TransactionRequest(Long userId, Long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }

}
